package com.smartclinic.webapp.controller;

import com.smartclinic.webapp.model.User;
import com.smartclinic.webapp.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        User user = new User(username, password);
        userRepository.save(user);

        return "redirect:/index.html";
    }
    @GetMapping("/users")
    public String listUsers(org.springframework.ui.Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "users";
    }
}