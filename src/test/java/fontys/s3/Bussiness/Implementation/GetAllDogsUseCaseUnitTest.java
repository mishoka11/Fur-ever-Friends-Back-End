package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.GetAllDogsRequest;
import fontys.s3.Domain.GetAllDogsResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAllDogsUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @Test
    void testGetAllDogs() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);
        GetAllDogsRequest request = new GetAllDogsRequest();

        List<DogEntity> dogs = Arrays.asList(
                new DogEntity(1L, "Fido", "Labrador", 5, 0),
                new DogEntity(2L, "Buddy", "Golden Retriever", 3, 0)
        );

        // Mock the behavior of dogRepository.findAll() method
        when(dogRepository.findAll()).thenReturn(dogs);

        // When
        GetAllDogsResponse response = useCase.getAllDogs(request);

        // Then
        assertEquals(dogs.size(), response.getDogs().size()); // Assert that the number of dogs returned matches the number of dogs in the repository
    }
}
