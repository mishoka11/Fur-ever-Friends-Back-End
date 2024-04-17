package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.GetDogResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class GetDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @Test
    void testGetDog() {
        MockitoAnnotations.initMocks(this);
        // Given
        GetDogUseCaseImplementation useCase = new GetDogUseCaseImplementation(dogRepository);
        long dogId = 1L;

        DogEntity dog = new DogEntity(dogId, "Fido", "Labrador", 5, 0);

        // Mock the behavior of dogRepository.findById() method
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(dog));

        // When
        GetDogResponse response = useCase.getDog(dogId);

        // Then
        assertEquals(dogId, response.getDogId()); // Assert that the dogId matches the requested dogId
    }
}
