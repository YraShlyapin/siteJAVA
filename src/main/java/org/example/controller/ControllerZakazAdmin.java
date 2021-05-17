package org.example.controller;

import org.example.models.Post;
import org.example.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasAuthority('Worker')")
public class ControllerZakazAdmin {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/zakaz")
    public String AllZakaz(Model model){
        ArrayList<Post> posts = new ArrayList<>();
        for (Post p: postRepository.findAll()){
            if (!p.getReady()){
                posts.add(p);
            }
        }
        model.addAttribute("message","");
        if (posts.toArray().length==0){
            model.addAttribute("message","Нет заказов");
        }
        model.addAttribute("posts",posts);

        return "/AllPizzaSQL";
    }

    @PostMapping("/zakaz")
    public String AllZakazPost(@RequestParam Long id){
        Post post;
        ArrayList<Post> posts = new ArrayList<>();
        for (Post p: postRepository.findAll()){
            if (p.getID().equals(id)){
                post=p;
                post.setReady(true);
                posts.add(post);
                continue;
            }
            posts.add(p);
        }
        postRepository.deleteAll();
        postRepository.saveAll(posts);
        return "redirect:/zakaz";
    }
}
