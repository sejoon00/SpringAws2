package com.sejoon.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    //소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성도니 사용자인지
    //처음 가입하는 사용자인지 판단하기 위한 메소드입니다.
    //Optional은 null이 들어있는 상태에서 참조되어도 NPE가 일어나지 않도록 해주는 wrapper 클래스이다
    Optional<User> findByEmail(String email);
}
