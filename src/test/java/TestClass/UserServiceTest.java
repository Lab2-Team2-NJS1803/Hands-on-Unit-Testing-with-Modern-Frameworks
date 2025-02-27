package org.example.lab2.Service;

import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Request.UserUpdateRequest;
import org.example.lab2.DTO.Response.UserResponse;
import org.example.lab2.Entity.User;
import org.example.lab2.Mapper.UserMapper;
import org.example.lab2.Reponsitory.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserResponse userResponse;
    private UserCreateRequest createRequest;
    private UserUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("123");
        user.setUsername("testUser");
        user.setPassword("password");
        user.setFirstname("Fat");
        user.setLastname("Dz");
        user.setDob(Date.valueOf("2003-04-13"));

        userResponse = new UserResponse();
        userResponse.setId("123");
        userResponse.setUsername("testUser");

        createRequest = new UserCreateRequest();
        createRequest.setUsername("newUser");
        createRequest.setPassword("newPass");

        updateRequest = new UserUpdateRequest();
        updateRequest.setUsername("updatedUser");
        updateRequest.setPassword("updatedPass");
    }

    @Test
    void testCreateUser() {
        when(userMapper.toUser(createRequest)).thenReturn(user);
        when(passwordEncoder.encode(createRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse response = userService.create(createRequest);

        assertNotNull(response);
        assertEquals("123", response.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        List<UserResponse> users = userService.getUsers();

        assertEquals(1, users.size());
        assertEquals("123", users.get(0).getId());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById("123")).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse response = userService.getUserById("123");

        assertNotNull(response);
        assertEquals("123", response.getId());
        verify(userRepository, times(1)).findById("123");
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById("123");

        userService.deleteUser("123");

        verify(userRepository, times(1)).deleteById("123");
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById("123")).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(updateRequest.getPassword())).thenReturn("updatedEncodedPassword");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        UserResponse response = userService.updateUser("123", updateRequest);

        assertNotNull(response);
        assertEquals("123", response.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUserNotFound() {
        when(userRepository.findById("999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser("999", updateRequest);
        });

        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById("999");
    }
}
