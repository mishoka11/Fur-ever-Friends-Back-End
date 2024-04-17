package fontys.s3.Bussiness.Implementation;

import fontys.s3.Persistence.Implementation.DogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class DeleteDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @Test
    void testDeleteDogShouldDeleteDog() {
        MockitoAnnotations.initMocks(this);

        // Given
        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
        long dogId = 1L;

        // When
        useCase.deleteDog(dogId);

        // Then
        verify(dogRepository).deleteById(dogId); // Verify that the method is called with the correct dogId
    }
}
