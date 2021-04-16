package com.coms.jd.web.controller.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDemo {
    @RequestMapping("/h")
    public String h(){
        return "success";
    }
}
