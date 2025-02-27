// src/test/java/TestClass/UserRepositoryTest.java
package TestClass;

import org.example.lab2.Entity.User;
import org.example.lab2.Reponsitory.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = org.example.lab2.Lab2Application.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testExistsUserByUsername() {
        User user = new User(null, "admin", "password123", "Fat", "Dz", Date.valueOf("2003-04-13"));
        userRepository.save(user);

        assertNotNull(user.getId(), "ID not auto create !!");

        boolean exists = userRepository.existsUserByUsername("admin");

        assertTrue(exists);
    }

    @Test
    void testExistsUserByUsername_NotFound() {
        // Check with a non-existent username
        boolean exists = userRepository.existsUserByUsername("nonexistent");

        // Expected result: false
        assertFalse(exists);
    }

}