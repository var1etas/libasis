package ru.filatov.libasis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.filatov.libasis.service.crud.UserService;

@SpringBootTest
public class SecurityTests {
    @Autowired
    UserService userService;
    @Test
    public void userDetailsTest() {
        System.out.println(userService.loadUserByUsername("Alex"));
    }
}
