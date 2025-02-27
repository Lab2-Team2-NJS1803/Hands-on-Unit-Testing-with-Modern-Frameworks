package org.example.lab2.Mapper;


import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Request.UserUpdateRequest;
import org.example.lab2.DTO.Response.UserResponse;
import org.example.lab2.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
