package ru.filatov.libasis.adapter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.filatov.libasis.adapter.web.LoginDto;
import ru.filatov.libasis.entity.UserEntity;
import ru.filatov.libasis.service.crud.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
    @PostMapping("/registration")
    public String adduser(UserEntity user, Model model)
    {
        try
        {
            System.out.println(user);
            userService.addUser(user);
            return "redirect:/login";
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

    @PostMapping("/login")
    public String login(LoginDto user, Model model){
        // TODO
        return "login";
    }
}
