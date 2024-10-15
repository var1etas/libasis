package ru.filatov.libasis.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.entity.UserEntity;
import ru.filatov.libasis.repository.UserRepository;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getUserByRole(@RequestParam String role) {
        return userRepository.findByRole(role);
    }

    @GetMapping("/html")
    public String greeting(@RequestParam String role, Model model) {
        UserEntity user = userRepository.findByRole(role).getFirst();
        model.addAttribute("user", user);
        return "user";
    }
}
