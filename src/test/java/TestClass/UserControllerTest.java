package TestClass;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.lab2.Controller.UserController;
import org.example.lab2.DTO.Request.ApiResponse;
import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Request.UserUpdateRequest;
import org.example.lab2.DTO.Response.UserResponse;
import org.example.lab2.Reponsitory.UserRepository;
import org.example.lab2.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private UserCreateRequest userCreateRequest;
    private UserUpdateRequest userUpdateRequest;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        userCreateRequest = new UserCreateRequest("1" , "admin", "password", "Fat", "Dz", Date.valueOf("2003-04-13"));
        userUpdateRequest = new UserUpdateRequest( "newUsername" , "newPassword", "Fat", "Dz", Date.valueOf("2003-04-13"));
        userResponse = new UserResponse("1 ","admin", "Fat", "Dz", Date.valueOf("2003-04-13"));

    }

    @Test
    void testCreateUser_Success() {
        when(userRepository.existsUserByUsername(userCreateRequest.getUsername())).thenReturn(false);
        when(userService.create(userCreateRequest)).thenReturn(userResponse);

        ApiResponse<UserResponse> response = userController.createUser(userCreateRequest);

        assertNotNull(response);
        assertEquals(userResponse, response.getResult());
        verify(userRepository, times(1)).existsUserByUsername(userCreateRequest.getUsername());
        verify(userService, times(1)).create(userCreateRequest);
    }

    @Test
    void testCreateUser_UsernameExists() {
        when(userRepository.existsUserByUsername(userCreateRequest.getUsername())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userController.createUser(userCreateRequest);
        });

        assertEquals("Username already exists", exception.getMessage());
        verify(userRepository, times(1)).existsUserByUsername(userCreateRequest.getUsername());
        verify(userService, never()).create(any());
    }

    @Test
    void testUpdateUser() {
        when(userService.updateUser("1", userUpdateRequest)).thenReturn(userResponse);

        ApiResponse<UserResponse> response = userController.updateUser("1", userUpdateRequest);

        assertNotNull(response);
        assertEquals(userResponse, response.getResult());
        verify(userService, times(1)).updateUser("1", userUpdateRequest);
    }

    @Test
    void testGetUsers() {
        List<UserResponse> userList = Arrays.asList(userResponse);
        when(userService.getUsers()).thenReturn(userList);

        ApiResponse<List<UserResponse>> response = userController.getUsers();

        assertNotNull(response);
        assertEquals(userList, response.getResult());
        verify(userService, times(1)).getUsers();
    }

    @Test
    void testGetUserById() {
        when(userService.getUserById("1")).thenReturn(userResponse);

        ApiResponse<UserResponse> response = userController.getUserById("1");

        assertNotNull(response);
        assertEquals(userResponse, response.getResult());
        verify(userService, times(1)).getUserById("1");
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userService).deleteUser("1");

        ApiResponse<String> response = userController.deleteUser("1");

        assertNotNull(response);
        assertEquals("Delete user successfully", response.getResult());
        verify(userService, times(1)).deleteUser("1");
    }

    @Test
    void testUsernameSizeValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UserCreateRequest invalidUser = new UserCreateRequest();
        invalidUser.setUsername("thisusernameistoolong"); // 19 characters, vượt quá giới hạn

        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(invalidUser);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Username must be between 1 and 18 characters")));
    }

    @Test
    void testPassWordSizeValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UserCreateRequest invalidUser = new UserCreateRequest();
        invalidUser.setPassword("thisusernameistoolong"); // 19 characters, vượt quá giới hạn

        Set<ConstraintViolation<UserCreateRequest>> violations = validator.validate(invalidUser);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Password must be between 1 and 18 characters")));
    }

}
