package fontys.s3.Domain_Tests.Dog_Domain_Tests;

import fontys.s3.Domain.DogDomain.DeleteDogRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteDogRequestTest {

    @Test
    void testDeleteDogRequest() {
        DeleteDogRequest request = DeleteDogRequest.builder()
                .dogId(1L)
                .build();

        assertEquals(1L, request.getDogId());
    }
}