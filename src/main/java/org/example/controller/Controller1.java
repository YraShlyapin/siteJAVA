package org.example.controller;

import org.example.logick.Colcul;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;


@Controller
public class Controller1 {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/java")
    public String java(){
        return "java";
    }
    @GetMapping("/people")
    public String people(Model model){

        model.addAttribute("a","все это стоит "+200+" рублей");
        return "people";
    }
    @GetMapping("/get-count")
    public String count(Model model,
                        @RequestParam(value = "a",required = false)Integer a){
        String coun = "кола стоит "+a+" рублей";
        model.addAttribute("a",coun);
        return "count";
    }
    @GetMapping("/colcul")
    public String colcul(Model model,
                         @RequestParam(value = "a",required = false)Integer a,
                         @RequestParam(value = "b",required = false)Integer b,
                         @RequestParam(value = "c",required = false)Character c){
        Colcul colcul = new Colcul(a,b,c);
        if (c=='p'){
            c='+';
        }
        if (c=='o'){
            c='%';
        }
        String answer = a+""+c.toString()+""+b+"="+colcul.answer();

        model.addAttribute("answer",answer);
        return "colcul";
    }
}
