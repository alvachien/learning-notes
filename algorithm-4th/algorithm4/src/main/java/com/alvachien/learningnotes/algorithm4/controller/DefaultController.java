package com.alvachien.learningnotes.algorithm4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public String Index() {
        return "Index page of Algorithm (4th.)";
    }

    @RequestMapping("/welcome")
    public String SayWelcome() {
        return "Welcome";
    }
}
