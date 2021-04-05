package com.bookfinder.mapper;

import com.bookfinder.domain.User;
import com.bookfinder.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO userDTO);
}
