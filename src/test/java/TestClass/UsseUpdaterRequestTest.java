package TestClass;

import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Request.UserUpdateRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsseUpdaterRequestTest {

    // Create a valid UserUpdateRequest with all fields populated
    @Test
    public void test_create_valid_user_update_request_with_all_fields() {
        // Arrange
        String username = "johndoe";
        String password = "password123";
        String firstname = "John";
        String lastname = "Doe";
        Date dob = new Date();

        // Act
        UserUpdateRequest request = UserUpdateRequest.builder()
                .username(username)
                .password(password)
                .firstname(firstname)
                .lastname(lastname)
                .dob(dob)
                .build();

        // Assert
        assertEquals(username, request.getUsername());
        assertEquals(password, request.getPassword());
        assertEquals(firstname, request.getFirstname());
        assertEquals(lastname, request.getLastname());
        assertEquals(dob, request.getDob());
    }
        // Create UserUpdateRequest with password exactly at min length (1 character)
        @Test
        public void test_create_user_update_request_with_min_length_password() {
            // Arrange
            String username = "johndoe";
            String password = "a"; // Minimum length of 1 character
            String firstname = "John";
            String lastname = "Doe";
            Date dob = new Date();

            // Act
            UserUpdateRequest request = UserUpdateRequest.builder()
                    .username(username)
                    .password(password)
                    .firstname(firstname)
                    .lastname(lastname)
                    .dob(dob)
                    .build();

            // Assert
            assertEquals(username, request.getUsername());
            assertEquals(password, request.getPassword());
            assertEquals(1, request.getPassword().length());
            assertEquals(firstname, request.getFirstname());
            assertEquals(lastname, request.getLastname());
            assertEquals(dob, request.getDob());
        }
}
