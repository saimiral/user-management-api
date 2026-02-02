package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.dto.PagedResponse;
import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.dto.UserUpdateDTO;
import org.springframework.data.domain.Pageable;

public interface UserService {
    PagedResponse<UserResponseDTO> getAllUsers(Pageable pageable);
    UserResponseDTO saveUser(UserCreateDTO dto);

    void deleteUser(Long id);

    UserResponseDTO updateUser(Long id, UserUpdateDTO dto);

    UserResponseDTO getUserById(Long id);

}
