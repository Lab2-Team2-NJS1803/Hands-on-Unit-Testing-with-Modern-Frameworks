package org.example.lab2.DTO.Request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
public class UserUpdateRequest implements Serializable {

    @Size(min = 1, max = 18)
    String password;
    String firstname;
    String lastname;
    Date dob;
}