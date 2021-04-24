package org.example.controller;

import org.example.logick.Colcul;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Map;


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
                         @RequestParam(value = "a",required = false, defaultValue = "0")Integer a,
                         @RequestParam(value = "b",required = false, defaultValue = "0")Integer b,
                         @RequestParam(value = "c",required = false, defaultValue = "p")Character c){
        Colcul colcul = new Colcul(a,b,c);
        if (c=='p'){
            c='+';
        }else if (c=='o'){
            c='%';
        }

        String answer = a+""+c.toString()+""+b+"="+colcul.answer();

        model.addAttribute("answer",answer);
        return "colcul";
    }
    @GetMapping("/pizza")
    public String pizza(Model model,
                        @RequestParam(value = "name",required = false, defaultValue = "name") String a,
                        @RequestParam(value = "a",required = false,defaultValue = "surname")String b,
                        @RequestParam(value = "c",required = false,defaultValue = "true")Boolean c){
        String isAcute;
        if (c){
            isAcute="острая";
        }else {
            isAcute="обычная";
        }
        model.addAttribute("name",a);
        model.addAttribute("surname",b);
        model.addAttribute("c",isAcute);
        return "pizza";
    }

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String surname, @RequestParam String patronymic, @RequestParam String Pizza, @RequestParam Boolean isAcute, Model model){
        Pizza pizza = new Pizza(name,surname,patronymic,Pizza,isAcute);
        ArrayList<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);
        model.addAttribute("piz",pizzas);

        return "pizza";
    }
}
class Pizza{
    String name;
    String surname;
    String patronymic;
    String pizza;
    Boolean isAcute;

    public Pizza(String name, String surname, String patronymic, String pizza, Boolean isAcute) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.pizza = pizza;
        this.isAcute = isAcute;

    }
}
