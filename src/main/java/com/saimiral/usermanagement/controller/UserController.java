package com.saimiral.usermanagement.controller;


import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<UserResponseDTO> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/users")
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateDTO dto){
        return service.saveUser(dto);
    }
}
