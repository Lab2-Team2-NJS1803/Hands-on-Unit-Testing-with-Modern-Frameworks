package org.example.lab2.DTO.Response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@ToString
public class UserResponse {
    String id;
    String username;
//    String password;
    String firstname;
    String lastname;
    Date dob;
}
