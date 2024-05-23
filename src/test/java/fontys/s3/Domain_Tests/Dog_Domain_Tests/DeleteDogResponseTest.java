package fontys.s3.Domain_Tests.Dog_Domain_Tests;


import fontys.s3.Domain.DogDomain.DeleteDogResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteDogResponseTest {

    @Test
    void testDeleteDogResponse() {
        DeleteDogResponse response = DeleteDogResponse.builder()
                .message("Dog deleted successfully")
                .build();

        assertEquals("Dog deleted successfully", response.getMessage());
    }
}