package fontys.s3.Domain_Tests.User_Domain_Tests;


import fontys.s3.Domain.UserDomain.UpdateUserResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateUserResponseTest {

    @Test
    void testUpdateUserResponse() {
        UpdateUserResponse response = UpdateUserResponse.builder()
                .message("User updated successfully")
                .build();

        assertEquals("User updated successfully", response.getMessage());
    }
}