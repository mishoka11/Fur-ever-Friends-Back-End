package fontys.s3.Domain_Tests.Dog_Domain_Tests;


import fontys.s3.Domain.DogDomain.Dog;
import fontys.s3.Persistence.Entity.Size;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {

    @Test
    void testDog() {
        Dog dog = Dog.builder()
                .id(1L)
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .dogYears(21)
                .information("Friendly dog")
                .size(Size.MEDIUM)
                .build();

        assertEquals(1L, dog.getId());
        assertEquals("Buddy", dog.getName());
        assertEquals("Golden Retriever", dog.getBreed());
        assertEquals(3, dog.getAge());
        assertEquals(21, dog.getDogYears());
        assertEquals("Friendly dog", dog.getInformation());
        assertEquals(Size.MEDIUM, dog.getSize());
    }
}