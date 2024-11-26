package ru.filatov.libasis.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.filatov.libasis.model.dto.UserRequestDto;
import ru.filatov.libasis.model.entity.UserEntity;
import ru.filatov.libasis.model.service.UserService;

@Validated
@RequestMapping("/api/user")
@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody @Valid UserRequestDto userRequestDto) {
        if(!userService.createUser(userRequestDto)) {
            return new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        if (!userService.updateUser(userRequestDto)) {
            return new ResponseEntity<>("Username not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<String> getUser(@PathVariable Long userId) {
        UserEntity user = userService.getUser(userId);
        return new ResponseEntity<>(user.toString(), HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        if(!userService.deleteUser(userId)) {
            return new ResponseEntity<>("Username to delete not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
