package com.saimiral.usermanagement.controller;


import com.saimiral.usermanagement.dto.*;
import com.saimiral.usermanagement.entity.User;
import com.saimiral.usermanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final UserService userService;

    public UserController(UserService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<UserResponseDTO>> getAllUsers (@RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge,
            @PageableDefault(size = 10, sort = "id") Pageable pageable){

        return ResponseEntity.ok(
                service.getAllUsers(pageable, minAge, maxAge)
        );
    }

    @PostMapping
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

    @GetMapping("/me")
    public UserResponseDTO getCurrentUser(Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getAge(),
                user.getEmail()
        );
    }

}
