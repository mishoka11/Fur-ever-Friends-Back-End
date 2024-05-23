package fontys.s3.Domain_Tests.User_Domain_Tests;

import fontys.s3.Domain.UserDomain.GetAllUsersRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetAllUsersRequestTest {

    @Test
    void testGetAllUsersRequest() {
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        assertNotNull(request);
    }
}