package com.saimiral.usermanagement.controller;


import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.entity.User;
import com.saimiral.usermanagement.service.UserService;
import jakarta.validation.Valid;
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
    public User createUser(@Valid @RequestBody UserCreateDTO dto){
        return service.saveUser(dto);
    }
}
