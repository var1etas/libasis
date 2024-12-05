package ru.filatov.libasis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.filatov.libasis.controller.dto.UserProfileDto;
import ru.filatov.libasis.model.entity.UserEntity;
import ru.filatov.libasis.model.service.UserService;

import java.util.List;

/**
 * Обработка запросов, связанных с регистрацией и просмотром данных аккаунта
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/all")
//    public String thUser(Model model) {
//        List<UserEntity> users = userService.getAllUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }

    /**
     * Возвращает форму регистрации пользователя
     */
    @GetMapping("/signUp")
    public String registration(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";
    }

    /**
     * Обрабатывает запросы на регистрацию пользователя в системе
     */
    @PostMapping("/signUp")
    public String createUser(@ModelAttribute UserEntity user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

    /**
     * Обрабатывает запросы на просмотр профиля
     */
    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        UserEntity user = userService.getUserByLogin(authentication.getName()).get();
        UserProfileDto userProfileDto = new UserProfileDto(user.getName(), user.getLogin(), user.getStatistic(), user.getReservesCount(), !user.getStatus());
        model.addAttribute("userProfile", userProfileDto);
        return "profile";
    }
}
