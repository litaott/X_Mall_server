package com.little.xmall.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class CookieController {

    @GetMapping("/login")
    public String getCookieInfo(@CookieValue(value = "user", defaultValue = "defaultUser") String user) {
        System.out.println("User cookie value: " + user);
        return "cookieInfo";
    }
}