package fontys.s3.Domain_Tests.User_Domain_Tests;


import fontys.s3.Domain.UserDomain.UpdateUserRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserRequestTest {

    @Test
    void testUpdateUserRequest() {
        UpdateUserRequest request = UpdateUserRequest.builder()
                .userId(1L)
                .username("updateduser")
                .email("updated@example.com")
                .password("newpassword")
                .build();

        assertEquals(1L, request.getUserId());
        assertEquals("updateduser", request.getUsername());
        assertEquals("updated@example.com", request.getEmail());
        assertEquals("newpassword", request.getPassword());
    }
}