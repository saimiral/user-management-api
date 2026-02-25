package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.dto.PagedResponse;
import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.dto.UserUpdateDTO;
import com.saimiral.usermanagement.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
    UserResponseDTO saveUser(UserCreateDTO dto);

    void deleteUser(Long id);

    UserResponseDTO updateUser(Long id, UserUpdateDTO dto);

    UserResponseDTO getUserById(Long id);

    PagedResponse<UserResponseDTO> getAllUsers(Pageable pageable, Integer minAge, Integer maxAge);

    User getUserByEmail(String email);
}
