package fontys.s3.Domain_Tests.User_Domain_Tests;


import fontys.s3.Domain.UserDomain.DeleteUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteUserResponseTest {

    @Test
    void testDeleteUserResponse() {
        DeleteUserResponse response = DeleteUserResponse.builder().message("User deleted successfully").build();

        assertEquals("User deleted successfully", response.getMessage());
    }
}