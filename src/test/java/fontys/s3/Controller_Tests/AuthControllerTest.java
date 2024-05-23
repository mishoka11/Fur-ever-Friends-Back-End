package fontys.s3.Controller_Tests;

import fontys.s3.Configuration.security.token.impl.JwtUtil;
import fontys.s3.Controller.AuthController;
import fontys.s3.Domain.AuthenticationDomain.LoginRequest;
import fontys.s3.Domain.AuthenticationDomain.LoginResponse;
import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAuthenticationToken_ShouldReturnJwtToken_WhenCredentialsAreCorrect() throws Exception {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password");
        UserDetails userDetails = mock(UserDetails.class);
        when(userService.loadUserByUsername(loginRequest.getEmail())).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("jwt-token");

        // Act
        ResponseEntity<?> response = authController.createAuthenticationToken(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof LoginResponse);
        assertEquals("jwt-token", ((LoginResponse) response.getBody()).getJwt());
    }

    @Test
    void createAuthenticationToken_ShouldThrowException_WhenCredentialsAreIncorrect() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("test@example.com", "wrongpassword");
        doThrow(new RuntimeException("Incorrect username or password"))
                .when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            authController.createAuthenticationToken(loginRequest);
        });
        assertEquals("Incorrect username or password", exception.getMessage());
    }

    @Test
    void createUser_ShouldReturnCreatedResponse_WhenUserIsCreatedSuccessfully() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .roles(Set.of("ROLE_USER"))
                .build();

        CreateUserResponse response = CreateUserResponse.builder()
                .userId(1L)
                .build();

        when(userService.createUser(request)).thenReturn(response);

        // Act
        ResponseEntity<CreateUserResponse> result = authController.createUser(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
