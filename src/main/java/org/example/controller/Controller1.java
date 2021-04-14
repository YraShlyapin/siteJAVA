package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Controller1 {
    @GetMapping("/")
    public String home(){
        return "index.html";
    }
    @GetMapping("/about")
    public String about(){
        return "about.html";
    }
    @GetMapping("/java")
    public String java(){
        return "java.html";
    }
}
