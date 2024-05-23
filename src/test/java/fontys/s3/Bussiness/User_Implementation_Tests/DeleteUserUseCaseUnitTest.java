package fontys.s3.Bussiness.User_Implementation_Tests;


import fontys.s3.Bussiness.Implementation.User_Impl.DeleteUserUCImpl;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class DeleteUserUseCaseUnitTest {

    @Mock
    private UserRepository userRepository;

    private DeleteUserUCImpl deleteUserUCImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deleteUserUCImpl = new DeleteUserUCImpl(userRepository);
    }

    @Test
    void testDeleteUser_Successful() {
        long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        deleteUserUCImpl.deleteUser(userId);

        // Verify that no exception is thrown
    }

    @Test
    void testDeleteUser_NonExistentUser() {
        long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> deleteUserUCImpl.deleteUser(userId));
    }

    @Test
    void testDeleteUser_InvalidUserId() {
        long userId = -1L;

        assertThrows(IllegalArgumentException.class, () -> deleteUserUCImpl.deleteUser(userId));
    }
}