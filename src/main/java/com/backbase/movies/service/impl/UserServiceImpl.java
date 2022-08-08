package com.backbase.movies.service.impl;

import com.backbase.movies.domain.User;
import com.backbase.movies.exception.UserAlreadyExistException;
import com.backbase.movies.repository.UserRepository;
import com.backbase.movies.service.UserService;
import com.backbase.movies.service.dto.UserDTO;
import com.backbase.movies.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor @Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDTO) {
        boolean isUserExist = this.userRepository.existsByUsername(userDTO.getUsername());
        if (isUserExist)
            throw new UserAlreadyExistException("user already exist");

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        log.info("saving new user {} to the database",userDTO.getName());
        User user = this.userMapper.toEntity(userDTO);
        userRepository.save(user);
        return userDTO;
    }

}
