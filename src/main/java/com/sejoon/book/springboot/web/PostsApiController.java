package com.sejoon.book.springboot.web;

import com.sejoon.book.springboot.service.posts.PostsService;
import com.sejoon.book.springboot.web.dto.PostsResponseDto;
import com.sejoon.book.springboot.web.dto.PostsSaveRequestDto;
import com.sejoon.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    //GET: 서버로 부터 데이터를 취득
    //POST: 서버에 데이터를 추가, 작성 등
    //PUT: 서버의 데이터를 갱신, 작성 등
    //DELETE: 서버의 데이터를 삭제

    //POST
    //URL에 변수(데이터)를 노출하지 않고 요청
    //데이터를 Body에 포함
    //URL에 데이터가 노출되지 않아 GET방식보다 보안↑
    //캐싱할수 없음

   @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        //@RequestBody는 데이터를 문자열로 받아와서
        //JSON 형식의 데이터를 자동으로 뒤에 오는 객체 형식으로 전환해서 받아온다.
        return postsService.save(requestDto); //Service에서 return 받는게 결국 id 값
    }

    @PutMapping("/api/v1/posts/{id}")
    //{id}를 @PathVariable Long id의 id에 넣어줌
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }


}
