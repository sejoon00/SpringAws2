package com.sejoon.book.springboot.domain.posts;


import com.sejoon.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
//Entity 객체에서는 Setter 메소드를 절대 만들지 않음
@NoArgsConstructor
//기본 생성자 자동 생성 == public Posts() {}
@Entity
//JPA 어노테이션이며 테이블과 링크될 클래스임을 나타냄
//카멜케이스를 언더스코어 네이밍(_)으로 테이블 이름을 매칭
public class Posts extends BaseTimeEntity {
    @Id
    //해당 테이블의 PK필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //PK 생성 규칙을 나타냄(Primary Key, 중복값을 가질 수 없는 특별히 선정된 키, 반드시 필요)
    //GenerationType.IDENTITY을 추가해야만 auto_increment가 됨 ->SQL과 관련됨
    private Long id;

    @Column(length = 500, nullable = false)
    //테이블의 칼럼을 나타내고 굳이 안해도 해당 클래스의 필드는 모두 칼럼이 됨
    //기본값 외에 추가로 변경할 옵션이 있으면 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    //해당 클래스의 빌더 패턴 클래스를 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    //빌더 클래스는 어느 필드에 어떤 값을 채워야 할지 명확하게 인지 가능(생성자와 차이점 알아야함)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
