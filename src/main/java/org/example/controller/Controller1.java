package org.example.controller;

import org.example.logick.Colcul;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class Controller1 {

    ArrayList<Zakaz> zakazs = new ArrayList<>();

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
        model.addAttribute("zakazs",zakazs);
        return "pizza";
    }

    @PostMapping("/abr")
    public String man(@RequestParam(value = "a",required = false,defaultValue = "неуказано") String a,
                      @RequestParam(value = "b",required = false,defaultValue = "неуказано") String b,
                      @RequestParam(value = "c",required = false,defaultValue = "неуказано") String c,
                      @RequestParam(value = "d",required = false) String d,
                      @RequestParam(value = "e",required = false,defaultValue = "false") Boolean e){
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        Zakaz zakaz = new Zakaz(a,b,c,d,e);
        zakazs.add(zakaz);
        for(Zakaz z:zakazs){
            System.out.println(z);
        }
        return "redirect:/pizza";
    }
}
class Zakaz {
    String a;
    String b;
    String c;
    String d;
    Boolean e;

    public Zakaz(String a, String b, String c, String d, Boolean e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    @Override
    public String toString() {
        return "Zakaz{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", e=" + e +
                '}';
    }
}