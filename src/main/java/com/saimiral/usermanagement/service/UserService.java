package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.entity.User;
import com.saimiral.usermanagement.exception.EmailAlreadyExistsException;
import com.saimiral.usermanagement.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<UserResponseDTO> getAllUsers(){
        return repository.findAll()
                .stream()
                .map(user -> {
                    return new UserResponseDTO(user.getId(), user.getName(), user.getAge());;
                })
                .toList();
    }

    public UserResponseDTO saveUser(UserCreateDTO dto){
        try {
            User user = new User(dto.getName(), dto.getAge());

            User savedUser = repository.save(user);

            return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getAge());
        } catch(DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
