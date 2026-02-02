package com.saimiral.usermanagement.controller;


import com.saimiral.usermanagement.dto.PagedResponse;
import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.dto.UserUpdateDTO;
import com.saimiral.usermanagement.service.UserService;
import com.saimiral.usermanagement.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public ResponseEntity<PagedResponse<UserResponseDTO>> getAllUsers(@PageableDefault(size = 10, sort = "id") Pageable pageable){
        return ResponseEntity.ok(service.getAllUsers(pageable));
    }

    @PostMapping("/users")
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateDTO dto){
        return service.saveUser(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto){
        UserResponseDTO updateUser = service.updateUser(id, dto);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(service.getUserById(id));
    }
}
