package org.example.lab2.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.lab2.DTO.Request.ApiResponse;
import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Request.UserUpdateRequest;
import org.example.lab2.DTO.Response.UserResponse;
import org.example.lab2.Reponsitory.UserRepository;
import org.example.lab2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userReponsitory;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest user) {
        ApiResponse<UserResponse> response = new ApiResponse<>();

        if(userReponsitory.existsUserByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        response.setResult(userService.create(user));
        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@PathVariable @Valid String id, @RequestBody UserUpdateRequest user) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.updateUser(id, user));
        return response;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getUsers());
        return response;
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable String id) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.getUserById(id));
        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable String id) {
        ApiResponse<String> response = new ApiResponse<>();
        userService.deleteUser(id);
        response.setResult("Delete user successfully");
        return response;
    }
}
