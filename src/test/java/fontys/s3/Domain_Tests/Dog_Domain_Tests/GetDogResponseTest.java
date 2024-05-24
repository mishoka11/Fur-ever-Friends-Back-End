package fontys.s3.Domain_Tests.Dog_Domain_Tests;


import fontys.s3.Domain.DogDomain.GetDogResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetDogResponseTest {

    @Test
    void testGetDogResponse() {
        GetDogResponse response = GetDogResponse.builder()
                .dogId(1L)
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .years(21)
                .build();

        assertEquals(1L, response.getDogId());
        assertEquals("Buddy", response.getName());
        assertEquals("Golden Retriever", response.getBreed());
        assertEquals(3, response.getAge());
        assertEquals(21, response.getYears());
    }
}