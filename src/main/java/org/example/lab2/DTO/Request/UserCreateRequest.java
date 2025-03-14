package org.example.lab2.DTO.Request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@ToString
public class UserCreateRequest implements Serializable {
    String id;
    @Size(min = 1, max = 18 , message = "Username must be between 1 and 18 characters")
    String username;
    @Size(min = 1, max = 18 , message = "Password must be between 1 and 18 characters")
    String password;
    String firstname;
    String lastname;
    Date dob;
}