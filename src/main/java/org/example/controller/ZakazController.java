package org.example.controller;

import org.example.models.Post;
import org.example.models.User;
import org.example.repo.PostRepository;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private UserRepository userRepository;

    ArrayList<Post> filterPosts = new ArrayList<>();
    ArrayList<Post> filterPosts1 = new ArrayList<>();
    ArrayList<Post> filterPosts2 = new ArrayList<>();
    ArrayList<Post> filterPosts3 = new ArrayList<>();
    ArrayList<Post> filterPosts4 = new ArrayList<>();

    int number = 0;

    @GetMapping("/blog")
    public String blog(Model model){
        Iterable<Post> posts = postRepository.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userFromDB = userRepository.findByUsername(authentication.getName());
        ArrayList<Post> posts1 = new ArrayList<>();
        for (Post p:posts){
            if (p.getIdUset()==userFromDB.getId()){
                posts1.add(p);
            }
        }
        model.addAttribute("posts",posts1);
        return "pizzaSQL";
    }
    @PostMapping("/blogAdd")
    public String blogAdd(@RequestParam(value = "name",required = false,defaultValue = "неуказано") String name,
                          @RequestParam(value = "surname",required = false,defaultValue = "неуказано") String surname,
                          @RequestParam(value = "patronymic",required = false,defaultValue = "неуказано") String patronymic,
                          @RequestParam(value = "pizzaType",required = false) String pizzaType,
                          @RequestParam(value = "isAcute",required = false,defaultValue = "false")Boolean isAcute){
        String isAcutes="";
        if (isAcute){
            isAcutes="острая";
        }else {
            isAcutes="обычная";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userFromDB = userRepository.findByUsername(authentication.getName());
        Post post = new Post(name,surname,patronymic,pizzaType,isAcutes,userFromDB.getId());
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blogDelite")
    public String blogDelite(@RequestParam Long id){
        postRepository.deleteById(id);
        return "redirect:/blog";
    }

    @PostMapping("/blogDeliteAll")
    public String blogDeliteAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userFromDB = userRepository.findByUsername(authentication.getName());
        Iterable<Post> posts = postRepository.findAll();
        for (Post p:posts){
            if (p.getIdUset()==userFromDB.getId()){
                postRepository.delete(p);
            }
        }
        return "redirect:/blog";
    }

    @GetMapping("/blogFilter")
    public String blogFilter(Model model){
        model.addAttribute("zakaz",filterPosts);
        model.addAttribute("zakaz1",filterPosts1);
        model.addAttribute("zakaz2",filterPosts2);
        model.addAttribute("zakaz3",filterPosts3);
        model.addAttribute("zakaz4",filterPosts4);
        return "blogFilterht";
    }
    @PostMapping("/blogFilter")
    public String postBlogFilter(){
        filterPosts = new ArrayList<>();
        filterPosts1 = new ArrayList<>();
        filterPosts2 = new ArrayList<>();
        filterPosts3 = new ArrayList<>();
        filterPosts4 = new ArrayList<>();
        return "redirect:/blogFilter";
    }

    @PostMapping("/filterBlog")
    public String filterBlog(@RequestParam(value = "name",required = false) String name,
                            @RequestParam(value = "surname",required = false) String surname,
                            @RequestParam(value = "patronymic",required = false) String patronymic,
                            @RequestParam(value = "pizzaType",required = false) String pizzaType,
                            @RequestParam(value = "isAcute",required = false,defaultValue = "false") Boolean isAcute){
        filterPosts = new ArrayList<>();
        filterPosts1 = new ArrayList<>();
        filterPosts2 = new ArrayList<>();
        filterPosts3 = new ArrayList<>();
        filterPosts4 = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userFromDB = userRepository.findByUsername(authentication.getName());
        Iterable<Post> posts = postRepository.findAll();
        ArrayList<Post> posts1 = new ArrayList<>();
        for (Post p:posts){
            if (p.getIdUset()==userFromDB.getId()){
                posts1.add(p);
            }
        }
        for (Post p:posts1){
            if (p.getName().equals(name)){
                filterPosts.add(p);
            }
            if (p.getSurname().equals(surname)){
                filterPosts1.add(p);
            }
            if (p.getPatronymic().equals(patronymic)){
                filterPosts2.add(p);
            }
            if (p.getPizzaType().equals(pizzaType)){
                filterPosts3.add(p);
            }
//            if (p.getIsAcute().equals()){
//                filterPosts4.add(p);
//            }else if (surname==null){
//
//            }
        }
        return "redirect:/blogFilter";
    }

    @GetMapping("/blogEditing")
    public String blogEditing(Model model){
        Iterable<Post> posts = postRepository.findAll();
        ArrayList<Post> posts1 = (ArrayList<Post>)postRepository.findAll();
        model.addAttribute("zakazs",posts);

        String acut = "";
        int count=0;
        for (Post p:posts){
            if (p.getID()==number){
                break;
            }
            count++;
        }
        if (posts1.get(count).getIsAcute().equals("острая")){
            acut="checked";
        }else {
            acut="false";
        }
        //TODO доделать PyzzaType
        model.addAttribute("number",count);
        model.addAttribute("acut",acut);
        return "blogEdit";
    }

    @PostMapping("/obrBlogEditing")
    public String obrBlogEditing(@RequestParam String as){
        number=Integer.parseInt(as);
        return "redirect:/blogEditing";
    }

    @PostMapping("/blogEditing")
    public String EditBlog(@RequestParam(value = "name",required = false,defaultValue = "неуказано") String name,
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userFromDB = userRepository.findByUsername(authentication.getName());
        Post post = new Post(name,surname,patronymic,pizzaType,acut,userFromDB.getId());
        Iterable<Post> posts = postRepository.findAll();
        ArrayList<Post> posts1 = new ArrayList<>();

        for (Post p:posts){
            if (p.getID()==number){
                posts1.add(post);
                continue;
            }
            posts1.add(p);
        }
        Iterable<Post> posts2 = posts1;
        postRepository.deleteAll();
        postRepository.saveAll(posts2);
        return "redirect:/blog";
    }



}