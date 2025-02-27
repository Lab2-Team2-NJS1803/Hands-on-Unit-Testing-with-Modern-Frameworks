package org.example.lab2.DTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class UserCreateRequest implements Serializable {
    String id;
    String username;
    String password;
    String firstname;
    String lastname;
    Date dob;
}