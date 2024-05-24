package fontys.s3.Controller_Tests;


import fontys.s3.Bussiness.Implementation.Login_Service.LoginUseCase;
import fontys.s3.Controller.LoginController;
import fontys.s3.Domain.AuthenticationDomain.LoginRequest;
import fontys.s3.Domain.AuthenticationDomain.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Mock
    private LoginUseCase loginUseCase;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_ShouldReturnCreatedResponse_WhenLoginIsSuccessful() {
        // Arrange
        LoginRequest request = new LoginRequest("user@example.com", "password");
        LoginResponse response = new LoginResponse("fake-jwt-token");
        when(loginUseCase.login(request)).thenReturn(response);

        // Act
        ResponseEntity<LoginResponse> result = loginController.login(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}