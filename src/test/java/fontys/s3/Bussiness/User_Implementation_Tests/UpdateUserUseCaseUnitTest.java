package fontys.s3.Bussiness.User_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.User_Impl.UpdateUserUCImpl;
import fontys.s3.Domain.UserDomain.UpdateUserRequest;
import fontys.s3.Domain.UserDomain.UpdateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Entity.UserTypeEntity;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UpdateUserUseCaseUnitTest {

    @Mock
    private UserRepository userRepository;

    private UpdateUserUCImpl updateUserUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateUserUseCaseImpl = new UpdateUserUCImpl(userRepository);
    }

    @Test
    void testUpdateUserShouldReturnUpdatedUser() {
        UserTypeEntity userType = UserTypeEntity.builder().id(1L).typeName("Regular").build();
        UserEntity existingUser = UserEntity.builder()
                .id(1L)
                .username("john_doe")
                .email("john.doe@example.com")
                .password("securepassword")
                .userType(userType)
                .roles(Set.of("Customer"))
                .build();

        UpdateUserRequest request = UpdateUserRequest.builder()
                .userId(1L)
                .username("jane_doe")
                .email("jane.doe@example.com")
                .password("newsecurepassword")
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(existingUser);

        UpdateUserResponse response = updateUserUseCaseImpl.updateUser(request);

        verify(userRepository).save(existingUser);
        assertEquals("User updated successfully", response.getMessage());
        assertEquals("jane_doe", existingUser.getUsername());
        assertEquals("jane.doe@example.com", existingUser.getEmail());
        assertEquals("newsecurepassword", existingUser.getPassword());
    }

    @Test
    void testUpdateUserShouldThrowExceptionWhenNotFound() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .userId(1L)
                .username("jane_doe")
                .email("jane.doe@example.com")
                .password("newsecurepassword")
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> updateUserUseCaseImpl.updateUser(request));
    }
}
