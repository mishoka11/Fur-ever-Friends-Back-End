package fontys.s3.Domain_Tests.User_Domain_Tests;


import fontys.s3.Domain.UserDomain.DeleteUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteUserRequestTest {

    @Test
    void testDeleteUserRequest() {
        DeleteUserRequest request = DeleteUserRequest.builder().userId(1L).build();

        assertEquals(1L, request.getUserId());
    }
}