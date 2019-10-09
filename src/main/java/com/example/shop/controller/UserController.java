package com.example.shop.controller;

import com.example.shop.been.User;
import com.example.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/show")
    public String showUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "show";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registration")
    public String regUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            model.addAttribute("message", "User is exist");
            return "registration";
        }
        User user2 = new User();
        user2.setUsername(username);
        user2.setPassword(password);
        userRepository.save(user2);
        return "redirect:/login";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(name = "id") Integer id, Model model) {
        List<User> userList = userRepository.findById(id);
        model.addAttribute("users", userList);
        return "show";
    }

    @PostMapping("/login")
    public String logUser(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "show";
        } else {
            model.addAttribute("message", "Incorrect login or password");
            return "login";
        }

    }
}
