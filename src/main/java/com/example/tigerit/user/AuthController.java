package com.example.tigerit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/register")
    Map<String, String> createNewUser(@RequestBody User user){
        return  userService.createNewUser(user);
    }

    @PostMapping(path = "/login")
    Map<String, String> login(@RequestBody User user){
        return  userService.loginUser(user);
    }
}
