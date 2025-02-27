package TestClass;

import org.example.lab2.DTO.Request.UserCreateRequest;
import org.example.lab2.DTO.Response.UserResponse;
import org.example.lab2.Entity.User;
import org.example.lab2.Mapper.UserMapper;
import org.example.lab2.Reponsitory.UserRepository;
import org.example.lab2.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userReponsitory;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void createUser_Success() {
        // Arrange (Chuẩn bị dữ liệu)
        UserCreateRequest request = new UserCreateRequest(null, "admin", "123456", "Fat", "Dz", Date.valueOf("2003-04-13"));
        User user = new User("1", "admin", "encodedPassword", "Fat", "Dz", Date.valueOf("2003-04-13"));
        UserResponse expectedResponse = new UserResponse("admin", "Fat", "Dz", Date.valueOf("2003-04-13"));

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userMapper.toUser(request)).thenReturn(user);
        when(userReponsitory.save(any(User.class))).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(expectedResponse);

        // Act (Gọi hàm cần test)
        UserResponse response = userService.create(request);

        //kiểm tra id của user
        assertEquals("1", user.getId());


        // Assert (Kiểm tra kết quả)
        assertNotNull(response);
        assertEquals(expectedResponse, response);

        // Kiểm tra repository có được gọi đúng không
        verify(userReponsitory, times(1)).save(any(User.class));
    }
}
