package com.sejoon.book.springboot.config.auth.Dto;

import com.sejoon.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
//인증된 사용자 정보만 필요 그 외에 필요한 정보들은 없으니 name, email, picture만 필드로 선언
//serializable을 상속받기 위해 User 말고 session을 추가적으로 생성 (User로 직접 사용하면 다른 엔티티와 관계에서 성능 이슈, 부수 효과가 발생할 확률이 높음)
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
