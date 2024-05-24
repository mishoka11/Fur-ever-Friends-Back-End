package fontys.s3.Domain_Tests.Dog_Domain_Tests;

import fontys.s3.Domain.DogDomain.Dog;
import fontys.s3.Domain.DogDomain.GetAllDogsResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllDogsResponseTest {

    @Test
    void testGetAllDogsResponse() {
        List<Dog> dogList = List.of(
                Dog.builder().id(1L).name("Buddy").build(),
                Dog.builder().id(2L).name("Max").build()
        );
        GetAllDogsResponse response = GetAllDogsResponse.builder().dogs(dogList).build();

        assertEquals(dogList, response.getDogs());
    }
}