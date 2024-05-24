package fontys.s3.Bussiness.User_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.User_Impl.GetUserUCImpl;
import fontys.s3.Domain.UserDomain.GetUserResponse;
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
import static org.mockito.Mockito.when;

class GetUserUseCaseUnitTest {

    @Mock
    private UserRepository userRepository;

    private GetUserUCImpl getUserUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getUserUseCaseImpl = new GetUserUCImpl(userRepository);
    }

    @Test
    void testGetUserShouldReturnUser() {
        UserTypeEntity userType = UserTypeEntity.builder().id(1L).typeName("Regular").build();
        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .username("john_doe")
                .email("john.doe@example.com")
                .password("securepassword")
                .userType(userType)
                .roles(Set.of("Customer"))
                .build();

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));

        GetUserResponse response = getUserUseCaseImpl.getUser(1L);

        assertEquals(1L, response.getUserId());
        assertEquals("john_doe", response.getUsername());
        assertEquals("john.doe@example.com", response.getEmail());
    }

    @Test
    void testGetUserShouldThrowExceptionWhenNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> getUserUseCaseImpl.getUser(1L));
    }
}
