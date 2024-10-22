package ru.filatov.libasis.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.entity.UserEntity;
import org.springframework.ui.Model;
import ru.filatov.libasis.service.crud.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getUserByRole(@RequestParam String role) {
        return userService.findByRole(role);
    }

    @GetMapping("/admins")
    public String getAdmins(Model model) {
        List<UserEntity> admins = userService.findByRole("admin");
        model.addAttribute("users", admins);
        return "user";
    }
}
