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

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public PagedResponse<UserResponseDTO> getAllUsers(Pageable pageable){

        if(pageable.getPageSize() > 50){
            throw new IllegalArgumentException("Page size cannot exceed 50");
        }

        Page<User> page = repository.findAll(pageable);

        List<UserResponseDTO> users = page.getContent()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return new PagedResponse<>(
                users,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public UserResponseDTO saveUser(UserCreateDTO dto){
        try {
            User user = new User(dto.getName(), dto.getAge());

            User savedUser = repository.save(user);

            return mapper.toResponse(savedUser);
        } catch(DataIntegrityViolationException ex) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
    }

    public void deleteUSer(Long id){
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
}
