package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.dto.PagedResponse;
import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.dto.UserUpdateDTO;
import com.saimiral.usermanagement.entity.User;
import com.saimiral.usermanagement.exception.EmailAlreadyExistsException;
import com.saimiral.usermanagement.exception.UserNotFoundException;
import com.saimiral.usermanagement.mapper.UserMapper;
import com.saimiral.usermanagement.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponseDTO saveUser(UserCreateDTO dto){
        try {
            User user = new User(dto.getName(), dto.getAge(), dto.getEmail());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));

            User savedUser = repository.save(user);

            return mapper.toResponse(savedUser);
        } catch(DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    public void deleteUser(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        repository.delete(user);
    }

    public UserResponseDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        User savedUser = repository.save(user);
        return mapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapper.toResponse(user);
    }

    public PagedResponse<UserResponseDTO> getAllUsers(Pageable pageable, Integer minAge, Integer maxAge){
        Page<User> page;

        if(minAge != null && maxAge != null){

            if(minAge > maxAge){
                throw new IllegalArgumentException("Age minimum cannot be greater than age maximum");
            }

            page = repository.findByAgeBetween(minAge, maxAge, pageable);

        }else{
            page = repository.findAll(pageable);
        }
        List<UserResponseDTO> user =
                page.getContent()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();
        return new PagedResponse<>(
                user,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
