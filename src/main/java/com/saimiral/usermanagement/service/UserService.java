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
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setName(user.getName());
                    dto.setAge(user.getAge());
                    return dto;
                })
                .toList();
    }

    public UserResponseDTO saveUser(UserCreateDTO dto){
        try {
            User user = new User(dto.getName(), dto.getAge());

            User savedUser = repository.save(user);

            UserResponseDTO response = new UserResponseDTO();
            response.setId(savedUser.getId());
            response.setName(savedUser.getName());
            response.setAge(savedUser.getAge());

            return response;
        } catch(DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
