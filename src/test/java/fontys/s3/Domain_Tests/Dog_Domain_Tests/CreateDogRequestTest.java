package fontys.s3.Domain_Tests.Dog_Domain_Tests;


import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Persistence.Entity.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateDogRequestTest {

    @Test
    void testCreateDogRequest() {
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .years(21)
                .information("Friendly dog")
                .size(Size.MEDIUM)
                .build();

        assertEquals("Buddy", request.getName());
        assertEquals("Golden Retriever", request.getBreed());
        assertEquals(3, request.getAge());
        assertEquals(21, request.getYears());
        assertEquals("Friendly dog", request.getInformation());
        assertEquals(Size.MEDIUM, request.getSize());
    }
}