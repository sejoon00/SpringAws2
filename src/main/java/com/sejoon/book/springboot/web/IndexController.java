package com.sejoon.book.springboot.web;

import com.sejoon.book.springboot.service.posts.PostsService;
import com.sejoon.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model)
    {
        //Model이란?
        //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음.
        model.addAttribute("posts", postsService.findAllDesc());
        //postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave()
    {
        return "posts-save";

    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        //@PathVariable {id}의 값을 id에 넣어준다.

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";

    }
}










//  Service 객체를 사용하기전 테스트
//    @GetMapping("/")
//    public String index() {
//        return "index";
//        //머스테치가 컨트롤러가 문자열을 반환할 때 자동으로 src/main.resources/templates/index.mustache로
//        //전환하여 View Resolver가 처리함
//    }
//

