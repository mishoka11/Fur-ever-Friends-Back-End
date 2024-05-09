package fontys.s3.Bussiness.Implementation;

import fontys.s3.Persistence.Implementation.DogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class DeleteDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;
//Happy flow tests

    @Test
    void testDeleteExistingDogShouldSucceed() {
        MockitoAnnotations.initMocks(this);

        // Given
        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
        long dogId = 1L;

        // When
        useCase.deleteDog(dogId);

        // Then
        verify(dogRepository).deleteById(dogId); // Verify that the method is called with the correct dogId
    }
    @Test
    void testDeleteNonExistingDogShouldThrowIllegalArgumentException() {
        MockitoAnnotations.initMocks(this);

        // Given
        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
        long nonExistingDogId = 99L; // Non-existing ID

        // When / Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> useCase.deleteDog(nonExistingDogId));
        assertEquals("Dog with ID " + nonExistingDogId + " does not exist", exception.getMessage());
    }
    @Test
    void testDeleteMultipleDogsShouldSucceed() {
        MockitoAnnotations.initMocks(this);

        // Given
        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
        long dogId1 = 1L;
        long dogId2 = 2L;

        // When
        useCase.deleteDog(dogId1);
        useCase.deleteDog(dogId2);

        // Then
        verify(dogRepository).deleteById(dogId1);
        verify(dogRepository).deleteById(dogId2);
    }
//Unhappy flow tests
@Test
void testDeleteDogWithNegativeIdShouldThrowException() {
    MockitoAnnotations.initMocks(this);

    // Given
    DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
    long negativeDogId = -1L;

    // When / Then
    assertThrows(IllegalArgumentException.class, () -> useCase.deleteDog(negativeDogId));
}
    @Test
    void testDeleteDogWithZeroIdShouldThrowException() {
        MockitoAnnotations.initMocks(this);

        // Given
        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
        long zeroDogId = 0L;

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> useCase.deleteDog(zeroDogId));
    }
    @Test
    void testDeleteNonExistingDogShouldThrowException() {
        MockitoAnnotations.initMocks(this);

        // Given
        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
        long nonExistingDogId = 99L; // Non-existing ID

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> useCase.deleteDog(nonExistingDogId));
    }
}
