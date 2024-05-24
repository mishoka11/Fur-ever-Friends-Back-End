package fontys.s3.Domain_Tests.User_Domain_Tests;

import fontys.s3.Domain.UserDomain.CreateUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserResponseTest {

    @Test
    void testCreateUserResponse() {
        CreateUserResponse response = CreateUserResponse.builder()
                .userId(1L)
                .build();

        assertEquals(1L, response.getUserId());
    }
}