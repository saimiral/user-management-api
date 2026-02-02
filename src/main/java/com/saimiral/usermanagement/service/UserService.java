package com.saimiral.usermanagement.service;

import com.saimiral.usermanagement.dto.PagedResponse;
import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    PagedResponse<UserResponseDTO> getAllUsers(Pageable pageable);
    UserResponseDTO saveUser(UserCreateDTO dto);
}
