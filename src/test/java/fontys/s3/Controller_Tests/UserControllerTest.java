package fontys.s3.Controller_Tests;

import fontys.s3.Controller.UserController;
import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUser_ShouldReturnUser_WhenUserExists() {
        // Arrange
        UserEntity user = new UserEntity();
        when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        // Act
        ResponseEntity<UserEntity> response = userController.getUser(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUser_ShouldReturnNotFound_WhenUserDoesNotExist() {
        // Arrange
        when(userService.findUserById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<UserEntity> response = userController.getUser(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getUsers_ShouldReturnListOfUsers() {
        // Arrange
        List<UserEntity> users = List.of(new UserEntity(), new UserEntity());
        when(userService.getAllUsers()).thenReturn(users);

        // Act
        ResponseEntity<List<UserEntity>> response = userController.getUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
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
        CreateUserResponse result = userController.createUser(request);

        // Assert
        assertEquals(response, result);
    }

    @Test
    void updateUser_ShouldUpdateUser_WhenUserIsUpdatedSuccessfully() {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .userId(1L)
                .username("updateduser")
                .email("updated@example.com")
                .password("newpassword")
                .roles(Set.of("ROLE_USER"))
                .build();

        // Act
        userController.updateUser(1L, request);

        // Assert
        verify(userService).updateUser(request);
    }

    @Test
    void deleteUser_ShouldDeleteUser_WhenUserIsDeletedSuccessfully() {
        // Act
        userController.deleteUser(1L);

        // Assert
        verify(userService).deleteUserById(1L);
    }
}
