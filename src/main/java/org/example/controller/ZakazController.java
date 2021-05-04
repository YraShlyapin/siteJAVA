package org.example.controller;

import org.example.logick.Colcul;
import org.example.logick.Zakaz;
import org.example.models.Post;
import org.example.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;


@Controller
public class ZakazController{

    @Autowired
    private PostRepository postRepository;

    ArrayList<Zakaz> zakazs = new ArrayList<>();
    ArrayList<Zakaz> filterZakazs = new ArrayList<>();
    int i = 0;
    int number=0;
    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts",posts);
        return "pizza";
    }


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
    public String pizza(Model model){
        model.addAttribute("zakazs",zakazs);
        return "pizza";
    }


    @GetMapping("/pizzaEditing")
    public String pizzaEditing(Model model){
        model.addAttribute("zakazs",zakazs);
        model.addAttribute("number",number);
        String acut = "";
        if (zakazs.get(number).isAcute=="острая"){
            acut="checked";
        }
        model.addAttribute("acut",acut);
        return "pizzaEdit";
    }

    @PostMapping("/obrPizzaEditing")
    public String obrPizzaEditing(@RequestParam String as){
        number=Integer.parseInt(as);
        return "redirect:/pizzaEditing";
    }

    @PostMapping("/EditPizza")
    public String EditPizza(@RequestParam(value = "name",required = false,defaultValue = "неуказано") String name,
                            @RequestParam(value = "surname",required = false,defaultValue = "неуказано") String surname,
                            @RequestParam(value = "patronymic",required = false,defaultValue = "неуказано") String patronymic,
                            @RequestParam(value = "pizzaType",required = false) String pizzaType,
                            @RequestParam(value = "isAcute",required = false,defaultValue = "false") Boolean isAcute){
        String acut = "";
        if (isAcute){
            acut="острая";
        }else {
            acut="обычная";
        }
        Zakaz zakaz = new Zakaz(name,surname,patronymic,pizzaType,acut,number);
        zakazs.set(number,zakaz);
        return "redirect:/pizza";
    }




    @GetMapping("/pizzaFilter")
    public String pizzaFilter(Model model){
        model.addAttribute("zakaz",filterZakazs);
        return "pizzaFilter";
    }

    @PostMapping("/filter")
    public String filter(){
        filterZakazs=new ArrayList<>();
        return "redirect:/pizzaFilter";
    }

    @PostMapping("/filtPizza")
    public String filtPizza(@RequestParam(value = "name",required = false) String name,
                            @RequestParam(value = "surname",required = false) String surname,
                            @RequestParam(value = "patronymic",required = false) String patronymic,
                            @RequestParam(value = "pizzaType",required = false) String pizzaType,
                            @RequestParam(value = "isAcute",required = false,defaultValue = "false") Boolean isAcute){
        if (name!=null){
            for (Zakaz z:zakazs){
                if (z.name.equals(name)){
                    filterZakazs.add(z);
                }
            }
        }
        if (surname!=null){
            for (Zakaz z:zakazs){
                if (z.surname.equals(surname)){
                    filterZakazs.add(z);
                }
            }
        }
        if (patronymic!=null){
            for (Zakaz z:zakazs){
                if (z.patronymic.equals(patronymic)){
                    filterZakazs.add(z);
                }
            }
        }
        if (pizzaType!=null){
            for (Zakaz z:zakazs){
                if (z.pizzaType.equals(pizzaType)){
                    filterZakazs.add(z);
                }
            }
        }
        String acut="";
        if (isAcute){
            acut="острая";
        }else {
            acut="обычная";
        }
        if (acut!=null){
            for (Zakaz z:zakazs){
                if (z.isAcute.equals(acut)){
                    filterZakazs.add(z);
                }
            }
        }
        return "redirect:/pizzaFilter";
    }

    @PostMapping("/abr")
    public String man(@RequestParam(value = "name",required = false,defaultValue = "неуказано") String name,
                      @RequestParam(value = "surname",required = false,defaultValue = "неуказано") String surname,
                      @RequestParam(value = "patronymic",required = false,defaultValue = "неуказано") String patronymic,
                      @RequestParam(value = "pizzaType",required = false) String pizzaType,
                      @RequestParam(value = "isAcute",required = false,defaultValue = "false") Boolean isAcute){
        String isAcutes="";
        if (isAcute){
            isAcutes="острая";
        }else {
            isAcutes="обычная";
        }
        Zakaz zakaz = new Zakaz(name,surname,patronymic,pizzaType,isAcutes,i);
        zakazs.add(zakaz);
        i++;
        return "redirect:/pizza";
    }

    @PostMapping("/obrDelite")
    public String obrDelite(@RequestParam(value = "as",required = false) String as){
        zakazs.remove(Integer.parseInt(as));
        int count2=0;
        for (Zakaz u:zakazs){
            u.id=count2;
            count2++;
        }
        i=zakazs.toArray().length-1;
        if(i<=0){
            i=0;
        }
        return "redirect:/pizza";
    }

    @PostMapping("/obrDeliteAll")
    public String obrDeliteAll(@RequestParam(value = "ss",required = false) String ss){
        zakazs = new ArrayList<>();
        i=0;
        return "redirect:/pizza";
    }
}