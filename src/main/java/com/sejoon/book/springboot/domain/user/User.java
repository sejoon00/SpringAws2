package com.sejoon.book.springboot.domain.user;

import com.sejoon.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    //JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정합니다.
    //기본적으로 int 된 숫자가 default값인데 데이터베이스에서 그 값이 무엇을 의미하는지 알기 위해
    //STRING으로 저장될 수 있도록 선언함
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role)
    {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture)
    {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }


}
