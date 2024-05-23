package fontys.s3.Bussiness.User_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.User_Impl.GetAllUsersUCImpl;
import fontys.s3.Domain.UserDomain.GetAllUsersRequest;
import fontys.s3.Domain.UserDomain.GetAllUsersResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Entity.UserTypeEntity;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAllUsersUseCaseUnitTest {

    @Mock
    private UserRepository userRepository;

    private GetAllUsersUCImpl getAllUsersUCImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        getAllUsersUCImpl = new GetAllUsersUCImpl(userRepository);
    }

    @Test
    void testGetAllUsers_Successful() {
        GetAllUsersRequest request = new GetAllUsersRequest();
        UserTypeEntity userType = UserTypeEntity.builder().id(1L).typeName("Regular").build();
        List<UserEntity> users = Arrays.asList(
                UserEntity.builder()
                        .id(1L)
                        .username("john_doe")
                        .email("john.doe@example.com")
                        .password("password")
                        .userType(userType)
                        .roles(Set.of("Customer"))
                        .build(),
                UserEntity.builder()
                        .id(2L)
                        .username("jane_doe")
                        .email("jane.doe@example.com")
                        .password("password")
                        .userType(userType)
                        .roles(Set.of("Customer"))
                        .build()
        );

        when(userRepository.findAll()).thenReturn(users);

        GetAllUsersResponse response = getAllUsersUCImpl.getAllUsers(request);

        assertEquals(users.size(), response.getUsers().size());
        assertEquals("john_doe", response.getUsers().get(0).getUsername());
        assertEquals("jane_doe", response.getUsers().get(1).getUsername());
    }
}
