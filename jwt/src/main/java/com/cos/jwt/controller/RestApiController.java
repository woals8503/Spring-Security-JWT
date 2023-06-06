package com.cos.jwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin  -> 인증이 필요한 요청은 다 거부됨, 로그인 해야만 한다면 이 어노테이션 사용 X
public class RestApiController {

    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";
    }
}
