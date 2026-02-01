package com.saimiral.usermanagement.controller;


import com.saimiral.usermanagement.entity.User;
import com.saimiral.usermanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return service.saveUser(user);
    }
}
