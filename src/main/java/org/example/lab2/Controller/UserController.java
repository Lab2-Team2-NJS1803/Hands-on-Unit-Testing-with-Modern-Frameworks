package org.example.lab2.Controller;

import lombok.extern.slf4j.Slf4j;
import org.example.lab2.DTO.Request.ApiResponse;
import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Response.UserResponse;
import org.example.lab2.Entity.User;
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

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody UserCreateRequest user) {
        ApiResponse<UserResponse> response = new ApiResponse<>();
        response.setResult(userService.create(user));
        return response;
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>();
        response.setResult(userService.getUsers());
        return response;
    }
}
