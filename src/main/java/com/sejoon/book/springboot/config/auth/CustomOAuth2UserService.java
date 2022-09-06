package com.sejoon.book.springboot.config.auth;

import com.sejoon.book.springboot.config.auth.Dto.OAuthAttributes;
import com.sejoon.book.springboot.config.auth.Dto.SessionUser;
import com.sejoon.book.springboot.domain.user.User;
import com.sejoon.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> { //OAuth2UserService 타입을 파라미터로 받고 서비스를 설정하기 때문에 반드시 상속받아야 함
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    //유저를 불러오면 판단하는 메서드. 실패해도 서비스에 지장을 주면 안되기 때문에 로그인 실패할때 예외 던져줌
    //이 메서드는 OAuth2 공급자로부터 액세스 토큰을 얻은 후에 호출됨.
    //http://yoonbumtae.com/?p=3000 여기를 참조해보자
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException
    {
        //delegate 대리자 만드는 이유가 무엇일까
        //DefalutOAuth2User 서비스를 통해 User 정보를 가져와야하기 때문에 대리자 생성
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //현재 로그인 진행중인 서비스를 구분하는 코드
        //지금은 구글만 사용하는 불필요한 값, 이후 네이버 로그인 연동 시에 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        //OAuth2 로그인 진행 시 키가 되는 필드값을 이야기함.PK와 같은 의미
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService를 통해 가져온 OAuth2User의 atrribute를 담을 클래스
        //이후 네이버 등 다른 소셜 로그인에도 이 클래스 사용
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        //로그인 한 유저 정보
        User user = saveOrUpate(attributes);

        //httpSession의 유저 속성을 설정
        //Object 형으로 업캐스팅하여 모두 받아냄
        httpSession.setAttribute("user", new SessionUser(user));

        //로그인한 유저를 리턴
        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                                                            attributes.getAttributes(),
                                                            attributes.getNameAttributekey());
    }

    //User 저장하고 이미 있는 데이터면 Update
    private User saveOrUpate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())//이메일로 user 찾기
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))//기존에 있으면 이름과 사진 변경
                .orElse(attributes.toEntity());//없으면 entity 새로 생성

        return userRepository.save(user);
    }


}
