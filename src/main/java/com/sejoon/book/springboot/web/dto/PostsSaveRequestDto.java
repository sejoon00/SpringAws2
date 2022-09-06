package com.sejoon.book.springboot.web.dto;


import com.sejoon.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Entity 클래스와 굉장히 유사하지만 Controller와 Service간 Request/Response 클래스를 따로 Dto로 만들어주는 이유
//Entity 클래스를 기준으로 테이블이 생성및 스키마가 변경 하지만 화면 변경은 사소한 기능 이를 위해 테이블과 연결된 Entity클래스를 변경하는건 너무 큰 변경
//Entity 클래스를 변경하면 많은 클래스에 영향이 끼치지만 Request/Response용 Dto는 View를 위한 클래스로 자주 변경이 필요함
//View Layer와 DB Layer는 철저하게 분리해야함 -> Entity와 Dto의 분리



@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {

        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
