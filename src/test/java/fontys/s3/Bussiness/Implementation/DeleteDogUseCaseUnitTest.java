package fontys.s3.Bussiness.Implementation;

import fontys.s3.Persistence.Implementation.DogRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;
//Happy flow tests
@Test
void testDeleteOneDogShouldSucceed() {
    MockitoAnnotations.initMocks(this);

    // Given
    DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
    long dogId = 1L;

    // Mock the behavior of dogRepository.existsById()
    when(dogRepository.existsById(dogId)).thenReturn(true); // Dog with ID 1 exists

    // When
    useCase.deleteDog(dogId);

    // Then
    // Verify that deleteById is called with the correct dogId
    verify(dogRepository).deleteById(dogId);
}

    @Test
    void testDeleteMultipleDogsShouldSucceed() {
        MockitoAnnotations.initMocks(this);

        // Given
        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(dogRepository);
        long dogId1 = 1L;
        long dogId2 = 2L;

        // Mock the behavior of dogRepository.existsById()
        when(dogRepository.existsById(dogId1)).thenReturn(true); // Dog with ID 1 exists
        when(dogRepository.existsById(dogId2)).thenReturn(true); // Dog with ID 2 exists

        // When
        useCase.deleteDog(dogId1);
        useCase.deleteDog(dogId2);

        // Then
        // Verify that deleteById is called for both dogs
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
