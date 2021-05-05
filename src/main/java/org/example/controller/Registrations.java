package org.example.controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
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
        User userFromDB = userRepository.findByUsername(user.getUserName());
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
}
