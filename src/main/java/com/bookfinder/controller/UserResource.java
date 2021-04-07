package com.bookfinder.controller;

import com.bookfinder.dto.UserDTO;
import com.bookfinder.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        userService.save(userDTO);
        return ResponseEntity.created(new URI("/api/users/" + userDTO.getId()))
                .build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) throws URISyntaxException {
        UserDTO updatedUser = userService.save(userDTO);
        return ResponseEntity.ok(updatedUser);
    }
}
