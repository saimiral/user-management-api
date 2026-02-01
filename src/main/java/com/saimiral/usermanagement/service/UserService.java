package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.entity.User;
import com.saimiral.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    public User saveUser(User user){
        return repository.save(user);
    }
}
