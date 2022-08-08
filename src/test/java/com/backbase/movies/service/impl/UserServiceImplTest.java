package com.backbase.movies.service.impl;

import com.backbase.movies.domain.User;
import com.backbase.movies.repository.UserRepository;
import com.backbase.movies.service.dto.UserDTO;
import com.backbase.movies.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper;

    @Spy
    private PasswordEncoder passwordEncoder;;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUser_shouldSave_thenStatus200() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("arezooAC");
        userDTO.setName("arezoo");
        userDTO.setPassword("123");

        User user = new User();
        user.setId(1L);
        user.setUsername("arezooAC");
        user.setName("arezoo");
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Mockito.doReturn(false).when(this.userRepository).existsByUsername("arezooA");
        Mockito.doReturn(user).when(this.userRepository).save(Mockito.any());

        UserDTO returnUser = this.userService.saveUser(userDTO);

        Assertions.assertNotNull(returnUser);
        Assertions.assertEquals("arezoo", returnUser.getName());
        Assertions.assertEquals("arezooAC", returnUser.getUsername());

        Mockito.verify(this.userRepository, Mockito.times(1)).existsByUsername("arezooAC");
        Mockito.verify(this.userRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(this.userMapper, Mockito.times(1)).toEntity(Mockito.any());
    }

}