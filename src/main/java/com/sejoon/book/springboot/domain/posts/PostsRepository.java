package com.sejoon.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Repository는 DB Layer 접근자임 (DAO 역할)
public interface PostsRepository extends JpaRepository<Posts, Long>//<Entity클래스, PK타입>
//이렇게 하면 기본적인 CRUD 메소드가 자동으로 생성
//Entity 객체랑 같은 패키지에 있어야함
{

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
