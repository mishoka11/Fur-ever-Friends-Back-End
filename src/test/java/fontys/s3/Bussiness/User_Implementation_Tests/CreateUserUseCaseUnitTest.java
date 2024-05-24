package fontys.s3.Bussiness.User_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.User_Impl.CreateUserUseCaseImpl;
import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateUserUseCaseUnitTest {

    @Mock
    private UserRepository userRepository;

    private CreateUserUseCaseImpl createUserUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createUserUseCaseImpl = new CreateUserUseCaseImpl(userRepository);
    }

    @Test
    void testCreateUserShouldReturnUserId() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("john_doe")
                .email("john.doe@example.com")
                .password("securepassword")
                .build();

        UserEntity savedUserEntity = UserEntity.builder()
                .id(1L)
                .username("john_doe")
                .email("john.doe@example.com")
                .password("securepassword")
                .build();

        when(userRepository.save(any(UserEntity.class))).thenReturn(savedUserEntity);

        CreateUserResponse response = createUserUseCaseImpl.createUser(request);

        assertEquals(1L, response.getUserId());
    }

    @Test
    void testCreateUserWithEmptyUsernameShouldThrowException() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("")
                .email("john.doe@example.com")
                .password("securepassword")
                .build();

        assertThrows(IllegalArgumentException.class, () -> createUserUseCaseImpl.createUser(request));
    }

    @Test
    void testCreateUserWithNullEmailShouldThrowException() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("john_doe")
                .email(null)
                .password("securepassword")
                .build();

        assertThrows(IllegalArgumentException.class, () -> createUserUseCaseImpl.createUser(request));
    }

    @Test
    void testCreateUserWithShortPasswordShouldThrowException() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("john_doe")
                .email("john.doe@example.com")
                .password("123")
                .build();

        assertThrows(IllegalArgumentException.class, () -> createUserUseCaseImpl.createUser(request));
    }
}
