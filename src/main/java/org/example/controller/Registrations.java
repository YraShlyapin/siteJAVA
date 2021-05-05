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

        if (userFromDB != null) {
//            model.addAttribute("massage", "пользовотель уже зарегестрирован");
//            System.out.println("хы лох");
//            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(password);
        user.setUserName(username);
        userRepository.save(user);
        return "redirect:/login";
    }
}
