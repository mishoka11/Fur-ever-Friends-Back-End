package fontys.s3.Domain_Tests.Dog_Domain_Tests;

import fontys.s3.Domain.DogDomain.GetDogRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetDogRequestTest {

    @Test
    void testGetDogRequest() {
        GetDogRequest request = GetDogRequest.builder().dogId(1L).build();

        assertEquals(1L, request.getDogId());
    }
}