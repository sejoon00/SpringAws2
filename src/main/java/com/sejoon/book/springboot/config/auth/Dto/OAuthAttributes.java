package com.sejoon.book.springboot.config.auth.Dto;

import com.sejoon.book.springboot.domain.user.Role;
import com.sejoon.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributekey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributekey, String name,
                           String email, String picture) {

        this.attributes = attributes;
        this.nameAttributekey= nameAttributekey;
        this.name = name;
        this.email = email;
        this.picture = picture;

    }

    //OAuth2User에서 반환하는 사용자 정보는 Map이어서 값 하나하나를 반환해야 함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder().name((String) attributes.get("name"))
                                        .email((String) attributes.get("email"))
                                        .picture((String) attributes.get("picture"))
                                        .attributes(attributes)
                                        .nameAttributekey(userNameAttributeName)
                                        .build();
    }

    //User 엔티티를 생성
    //OAuthAttributes에서 엔티티를 생성한느 시점은 처음 가입할 때임
    //가입할 때 기본 권한을 GUEST로 주기 위해서 role빌더 값에는 Role.GUEST를 사용
    public User toEntity() {
        return User.builder().name(name).email(email).picture(picture).role(Role.GUEST).build();

    }
}
