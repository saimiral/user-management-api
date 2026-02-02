package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.entity.User;
import com.saimiral.usermanagement.exception.EmailAlreadyExistsException;
import com.saimiral.usermanagement.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    private UserResponseDTO mapToResponse(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getAge());
    }

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers(){
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)

                .toList();
    }

    public UserResponseDTO saveUser(UserCreateDTO dto){
        try {
            User user = new User(dto.getName(), dto.getAge());

            User savedUser = repository.save(user);

            return mapToResponse(savedUser);
        } catch(DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }
}
