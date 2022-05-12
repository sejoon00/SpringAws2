package com.sejoon.book.springboot.web;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
//참고
//mocking이란? -> 테스트를 위해 실제 객체와 비슷한 모의 객체를 만드는 것
//실제 객체와 비슷한 가짜 객체를 만들어서 테스트에 필요한 기능만 가지도록 모킹하면 데스트가 쉬워진다.
//객체가 복잡한 의존성을 가지고 있을 때, 모킹한 객체를 이용하면 의존성을 단절시킬수 있어 쉽게 테스트가 가능
//웹 환경에서 '컨트롤러'를 테스트하고자 하면 반드시 서블릿 컨테이너가 구동되고 DispatcherServlet 객체가
//메모리에 올라가야 하지만, 서블릿 컨테이너를 모킹하면 테스트용 모형 컨테이너를 사용해 간단하게 '컨트롤러'를 테스트가 가능하다.


//servlet이란? -> Notion을 참고하자

@RunWith(SpringRunner.class)
//SpringBootTest만 사용하면 apllication context를 전부 로딩하여 무거운 프로젝트가 될 수 있는데
//RunWith(SpringRunner.class)을 사용하면 @AutoWired 와 @MockBean이 붙은것들만 로딩한다.
//Junit 5.x이상 부터는 SpringBootTest에 자체에 내장되어 있다.
@SpringBootTest(webEnvironment = RANDOM_PORT)
//테스트의 웹 환경을 설정하는 속성 (기본값을 MOCK이고 서블릿 컨테이너를 mocking한 것을 띄어준다.)
//RANDOM_PORT로 설정하면 서블릿 컨테이너를 띄어주게 바뀐다.
//실제 http 서버에서 테스트를 할 수 있고 TestRemplate가 필요하게 된다.
public class IndexControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;
    //우리가 webEnvironment를 = RANDOM_PORT로 설정함으로써 서블릿 컨테이너를 띄어주게 되고
    //이에 따라 서블릿 컨테이너를 직접 실행하는 TestRestTemplate를 생성해주었다.

    @Test
    public void 메인페이지_로딩() {
        //when
        String body = this.restTemplate.getForObject("/", String.class);
        //주어진 URL에서 HTTP GET메소드를 통해 객체로 결과를 반환받는다.
        //지금은 mustache의 HTML 코드를 string으로 받고 있다.
        //mustache가 "index" string을 자동으로 index.mustache로 바꾸어준다.


        //then
        Assertions.assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");

    }


}
