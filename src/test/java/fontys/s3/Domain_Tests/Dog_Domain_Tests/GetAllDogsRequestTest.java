package fontys.s3.Domain_Tests.Dog_Domain_Tests;


import fontys.s3.Domain.DogDomain.GetAllDogsRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GetAllDogsRequestTest {

    @Test
    void testGetAllDogsRequest() {
        GetAllDogsRequest request = GetAllDogsRequest.builder().build();
        assertNotNull(request);
    }
}
