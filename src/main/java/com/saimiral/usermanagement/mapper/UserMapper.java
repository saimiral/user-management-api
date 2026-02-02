package com.saimiral.usermanagement.mapper;

import com.saimiral.usermanagement.dto.UserCreateDTO;
import com.saimiral.usermanagement.dto.UserResponseDTO;
import com.saimiral.usermanagement.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponse(User user){
        return new UserResponseDTO(user.getId(), user.getName(), user.getAge());
    }

    public User toEntity(UserCreateDTO dto){
        return new User(dto.getName(), dto.getAge());
    }
}
