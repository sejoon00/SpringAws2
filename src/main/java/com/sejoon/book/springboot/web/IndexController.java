package com.sejoon.book.springboot.web;

import com.sejoon.book.springboot.config.auth.Dto.SessionUser;
import com.sejoon.book.springboot.service.posts.PostsService;
import com.sejoon.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")//머스테치 스터터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.(즉, src/main/resources/templates가 앞에 .mustache가 뒤에 자동으로 붙음)
    //"/"기본 url이 오면 index.muastache가 실행됨
    public String index(Model model)
    {
        //Model이란?
        //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음.
        //data를 model에 담아서 View 넘길때 사용한다.
        model.addAttribute("posts", postsService.findAllDesc());
        //addAttribute("변수이름", 변수에 넣을 데이터값)
        //postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.

        //key 가 "user"인 객체 리턴
        SessionUser user = (SessionUser) httpSession.getAttribute("user");//set할때 업캐스팅 했던걸 다시 다운 캐스팅함

        if(user != null)
            model.addAttribute("userName",user.getName());//model에 userName이라는 변수명으로 user 이름 저장

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

