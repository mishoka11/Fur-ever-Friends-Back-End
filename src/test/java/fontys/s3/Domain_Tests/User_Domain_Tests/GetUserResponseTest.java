package fontys.s3.Domain_Tests.User_Domain_Tests;


import fontys.s3.Domain.UserDomain.GetUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetUserResponseTest {

    @Test
    void testGetUserResponse() {
        GetUserResponse response = GetUserResponse.builder()
                .userId(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .build();

        assertEquals(1L, response.getUserId());
        assertEquals("testuser", response.getUsername());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("password", response.getPassword());
    }
}