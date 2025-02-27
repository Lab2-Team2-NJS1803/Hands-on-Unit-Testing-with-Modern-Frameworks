package org.example.lab2.Service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Response.UserResponse;
import org.example.lab2.Entity.User;
import org.example.lab2.Mapper.UserMapper;
import org.example.lab2.Reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserService {

    UserReponsitory userReponsitory;
    PasswordEncoder passwordEncoder;
    UserMapper userMapper;

    public UserResponse create(UserCreateRequest request){

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        log.info("Create user: {}", user);
        return userMapper.toUserResponse(userReponsitory.save(user));
    }


    public List<UserResponse> getUsers() {
        log.info("Get all users");
        return userReponsitory.findAll().stream()
                .map(user -> userMapper.toUserResponse(user))
                .collect(Collectors.toList());
    }

}
