package TestClass;

import org.example.lab2.DTO.Request.UserCreateRequest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsserCreateRequestTest {

    // Create a valid UserCreateRequest with all fields populated
    @Test
    public void test_create_valid_user_request_with_all_fields() {
        // Arrange
        String id = "123";
        String username = "testuser";
        String password = "password123";
        String firstname = "John";
        String lastname = "Doe";
        Date dob = new Date();

        // Act
        UserCreateRequest request = UserCreateRequest.builder()
                .id(id)
                .username(username)
                .password(password)
                .firstname(firstname)
                .lastname(lastname)
                .dob(dob)
                .build();

        // Assert
        assertEquals(id, request.getId());
        assertEquals(username, request.getUsername());
        assertEquals(password, request.getPassword());
        assertEquals(firstname, request.getFirstname());
        assertEquals(lastname, request.getLastname());
        assertEquals(dob, request.getDob());

    }

    // Create UserCreateRequest with null username
    @Test
    public void test_create_user_request_with_null_username() {
        // Arrange
        String id = "456";
        String password = "securepass";
        String firstname = "Jane";
        String lastname = "Smith";
        Date dob = new Date();

        // Act
        UserCreateRequest request = UserCreateRequest.builder()
                .id(id)
                .username(null)
                .password(password)
                .firstname(firstname)
                .lastname(lastname)
                .dob(dob)
                .build();

        // Assert
        assertEquals(id, request.getId());
        assertNull(request.getUsername());
        assertEquals(password, request.getPassword());
        assertEquals(firstname, request.getFirstname());
        assertEquals(lastname, request.getLastname());
        assertEquals(dob, request.getDob());
    }
}
