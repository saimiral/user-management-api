package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.dto.UserCreateDTO;
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

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User saveUser(UserCreateDTO dto){
        try {
            User user = new User();
            user.setName(dto.getName());
            user.setAge(dto.getAge());
            return repository.save(user);
        } catch(DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
