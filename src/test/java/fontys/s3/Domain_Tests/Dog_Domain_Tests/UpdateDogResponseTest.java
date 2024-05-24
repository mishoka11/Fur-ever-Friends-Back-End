package fontys.s3.Domain_Tests.Dog_Domain_Tests;


import fontys.s3.Domain.DogDomain.UpdateDogResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateDogResponseTest {

    @Test
    void testUpdateDogResponse() {
        UpdateDogResponse response = UpdateDogResponse.builder()
                .message("Dog updated successfully")
                .build();

        assertEquals("Dog updated successfully", response.getMessage());
    }
}