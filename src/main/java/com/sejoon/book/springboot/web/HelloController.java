package com.sejoon.book.springboot.web;

import com.sejoon.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//GET: 서버로 부터 데이터를 취득
//POST: 서버에 데이터를 추가, 작성 등
//PUT: 서버의 데이터를 갱신, 작성 등
//DELETE: 서버의 데이터를 삭제

@RestController
//JSON을 반환하는 컨트롤러를 만들어 줌
public class HelloController {
    @GetMapping("/hello")
    //GET요청을 받을 수 있는 API 생성
    //GET서버로부터 데이터를 취득, 오로지 읽을 때만 사용
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }

}
