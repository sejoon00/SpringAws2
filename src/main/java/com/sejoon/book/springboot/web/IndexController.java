package com.sejoon.book.springboot.web;

import com.sejoon.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}











//    @GetMapping("/")
//    public String index() {
//        return "index";
//        //머스테치가 컨트롤러가 문자열을 반환할 때 자동으로 src/main.resources/templates/index.mustache로
//        //전환하여 View Resolver가 처리함
//    }
//
//    @GetMapping("/posts/save")
//    public String postsSave()
//    {
//        return "posts-save";
//
//}
