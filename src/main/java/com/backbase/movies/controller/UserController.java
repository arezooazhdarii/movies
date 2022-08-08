package com.backbase.movies.controller;

import com.backbase.movies.domain.User;
import com.backbase.movies.service.UserService;
import com.backbase.movies.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok().body(userService.saveUser(userDTO));
    }

}

