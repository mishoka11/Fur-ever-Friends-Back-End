package fontys.s3.Domain_Tests.User_Domain_Tests;

import fontys.s3.Domain.UserDomain.CreateUserRequest;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateUserRequestTest {

    @Test
    void testCreateUserRequest() {
        CreateUserRequest request = CreateUserRequest.builder()
                .userId(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .roles(Set.of("ROLE_USER"))
                .build();

        assertEquals(1L, request.getUserId());
        assertEquals("testuser", request.getUsername());
        assertEquals("test@example.com", request.getEmail());
        assertEquals("password", request.getPassword());
        assertEquals(Set.of("ROLE_USER"), request.getRoles());
    }
}