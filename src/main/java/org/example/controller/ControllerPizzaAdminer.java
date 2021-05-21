package org.example.controller;

import org.example.models.Pizza;
import org.example.repo.PizzaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@PreAuthorize("hasAuthority('Admin')")
public class ControllerPizzaAdminer {

    @Autowired
    private PizzaRepo pizzaRepo;

    Long num;

    @GetMapping("/AdminPanel")
    public String AdminPanel(Model model){
        model.addAttribute("pizza",pizzaRepo.findAll());
        return "AdminPanel";
    }
    @PostMapping("/AdminPanelPost")
    public String AdminPanelPost(@RequestParam String name,
                                 @RequestParam String Sostav,
                                 @RequestParam String URL,
                                 @RequestParam int price){
        Pizza pizza = new Pizza(name,URL,Sostav,price);
        pizzaRepo.save(pizza);
        return "redirect:/AdminPanel";
    }
    @PostMapping("/pizzaDelite")
    public String pizzaDelite(@RequestParam Long ID){
        System.out.println("asd");
        pizzaRepo.deleteById(ID);
        return "redirect:/AdminPanel";
    }
    @GetMapping("/pizzaEditing")
    public String pizzaEditing(Model model){
        Pizza pizza = new Pizza();
        for (Pizza p:pizzaRepo.findAll()){
            if (p.getID().equals(num)){
                pizza = p;
            }
        }
        model.addAttribute("pizza",pizza);
        return "PizzaEditing";
    }
    @PostMapping("/pizzaEditing")
    public String pizzaEditingPost(@RequestParam Long id){
        num=id;
        return "redirect:/pizzaEditing";
    }
    @PostMapping("/AdminPanelPostEdite")
    public String AdminPanelPostEdite(@RequestParam String name,
                                      @RequestParam String Sostav,
                                      @RequestParam String URL,
                                      @RequestParam int price){
        ArrayList<Pizza> pizza =  new ArrayList<>();
        for (Pizza p:pizzaRepo.findAll()){
            if (p.getID().equals(num)) {
                pizza.add(new Pizza(name,URL,Sostav,price));
                continue;
            }
            pizza.add(p);
        }
        pizzaRepo.deleteAll();
        pizzaRepo.saveAll(pizza);
        return "redirect:/AdminPanel";
    }
}
