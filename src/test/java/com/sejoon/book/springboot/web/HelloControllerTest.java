package com.sejoon.book.springboot.web;

import junit.framework.TestCase;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.regex.Matcher;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest( controllers = HelloController.class)
public class HelloControllerTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void hello리턴() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto리턴() throws Exception {
        String name = "hello";
        int amount = 10000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        //API테스트할 때 사용될 요청 파라미터 설정
                        //String만 허용
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                //JSON 응답ㄱ밧을 필드별로 검증할 수 있는 메소드입니다.
                //$.을 앞에 붙여 사용합니다.
                .andExpect(jsonPath("$.amount", is(amount)));

    }


}