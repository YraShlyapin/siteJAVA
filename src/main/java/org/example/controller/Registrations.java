package org.example.controller;

import org.example.models.Role;
import org.example.models.User;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class Registrations {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Model model,
                          @RequestParam String username,
                          @RequestParam String password){
        Iterable<User> users = userRepository.findAll();
        for (User u:users){
            if (u.getUserName().equals(username)){
                model.addAttribute("message", "пользовотель уже зарегестрирован");
                return "registration";
            }
        }
        model.addAttribute("message", "");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(password);
        user.setUserName(username);
        userRepository.save(user);
        return "redirect:/login";
    }
    @GetMapping("/registrationWorker")
    public String registrationWorker(){
        return "registrationWorker";
    }
    @PostMapping("/registrationWorker")
    public String addWorker(User user, Model model,
                            @RequestParam String username,
                            @RequestParam String password,
                            @RequestParam String code){
        Iterable<User> users = userRepository.findAll();
        for (User u:users){
            if (u.getUserName().equals(username)){
                model.addAttribute("message", "пользовотель уже зарегестрирован");
                return "registrationWorker";
            }
        }
        if (!code.equals("ad123dd")){
            model.addAttribute("message", "неправельный код");
            System.out.println("asd");
            return "registrationWorker";
        }
        System.out.println("aa");
        model.addAttribute("message", "");
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.Worker));
        user.setPassword(password);
        user.setUserName(username);
        userRepository.save(user);
        return "redirect:/login";
    }
}
