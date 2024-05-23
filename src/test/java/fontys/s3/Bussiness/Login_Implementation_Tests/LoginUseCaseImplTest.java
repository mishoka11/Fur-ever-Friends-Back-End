package fontys.s3.Bussiness.Login_Implementation_Tests;

import fontys.s3.Bussiness.Exception.InvalidCredentialsException;
import fontys.s3.Configuration.security.token.AccessTokenEncoder;
import fontys.s3.Configuration.security.token.impl.AccessTokenImpl;
import fontys.s3.Bussiness.Implementation.Login_Service.LoginUseCaseImpl;
import fontys.s3.Domain.AuthenticationDomain.LoginRequest;
import fontys.s3.Domain.AuthenticationDomain.LoginResponse;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Entity.UserTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginUseCaseImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccessTokenEncoder accessTokenEncoder;

    private LoginUseCaseImpl loginUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginUseCaseImpl = new LoginUseCaseImpl(userRepository, passwordEncoder, accessTokenEncoder);
    }

    @Test
    void testLogin_Successful() {
        UserTypeEntity userType = UserTypeEntity.builder().id(1L).typeName("Regular").build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("john.doe@example.com")
                .password("securepassword")
                .userTypes(Set.of(userType))
                .build();

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(userEntity);
        when(passwordEncoder.matches("password", "securepassword")).thenReturn(true);
        when(accessTokenEncoder.encode(any(AccessTokenImpl.class))).thenReturn("mocked_token");

        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "password");
        LoginResponse loginResponse = loginUseCaseImpl.login(loginRequest);

        assertNotNull(loginResponse);
        assertEquals("mocked_token", loginResponse.getJwt());
    }

    @Test
    void testLogin_UserNotFound() {
        when(userRepository.findByEmail("unknown@example.com")).thenReturn(null);

        LoginRequest loginRequest = new LoginRequest("unknown@example.com", "password");

        assertThrows(InvalidCredentialsException.class, () -> loginUseCaseImpl.login(loginRequest));
    }

    @Test
    void testLogin_InvalidPassword() {
        UserTypeEntity userType = UserTypeEntity.builder().id(1L).typeName("Regular").build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .email("john.doe@example.com")
                .password("securepassword")
                .userTypes(Set.of(userType))
                .build();

        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(userEntity);
        when(passwordEncoder.matches("wrong_password", "securepassword")).thenReturn(false);

        LoginRequest loginRequest = new LoginRequest("john.doe@example.com", "wrong_password");

        assertThrows(InvalidCredentialsException.class, () -> loginUseCaseImpl.login(loginRequest));
    }
}