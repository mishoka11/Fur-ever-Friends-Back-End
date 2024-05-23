package fontys.s3.Domain_Tests.Dog_Domain_Tests;


import fontys.s3.Domain.DogDomain.UpdateDogRequest;
import fontys.s3.Persistence.Entity.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdateDogRequestTest {

    @Test
    void testUpdateDogRequest() {
        UpdateDogRequest request = UpdateDogRequest.builder()
                .dogId(1L)
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .years(21)
                .information("Friendly")
                .size(Size.LARGE)
                .build();

        assertEquals(1L, request.getDogId());
        assertEquals("Buddy", request.getName());
        assertEquals("Golden Retriever", request.getBreed());
        assertEquals(3, request.getAge());
        assertEquals(21, request.getYears());
        assertEquals("Friendly", request.getInformation());
        assertEquals(Size.LARGE, request.getSize());
    }
}