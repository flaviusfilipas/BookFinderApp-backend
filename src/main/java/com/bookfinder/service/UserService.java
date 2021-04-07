package com.bookfinder.service;

import com.bookfinder.domain.User;
import com.bookfinder.dto.UserDTO;
import com.bookfinder.mapper.UserMapper;
import com.bookfinder.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO save(UserDTO userDTO){
        User entity = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(entity);
        return userMapper.toDto(savedUser);
    }
}
