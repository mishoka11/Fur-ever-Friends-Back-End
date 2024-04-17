package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.UpdateDogRequest;
import fontys.s3.Domain.UpdateDogResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @Test
    void testUpdateDogShouldReturnUpdatedDog() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(dogRepository);
        long dogId = 1L;
        UpdateDogRequest request = new UpdateDogRequest(dogId, "Fido", "Labrador", 5, 0);

        DogEntity existingDog = new DogEntity(dogId, "Buddy", "Golden Retriever", 3, 0);

        // Mock the behavior of dogRepository.findById() method
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(existingDog));

        // When
        UpdateDogResponse response = useCase.updateDog(request);

        // Then
        verify(dogRepository).save(existingDog); // Verify that dogRepository.save() is called with the existingDog
        assertEquals("Dog updated successfully", response.getMessage()); // Assert the success message
        assertEquals("Fido", existingDog.getName()); // Assert that the name of the existing dog is updated
    }

    @Test
    void testUpdateNonExistingDog() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(dogRepository);
        long dogId = 1L;
        UpdateDogRequest request = new UpdateDogRequest(dogId, "Fido", "Labrador", 5, 0);

        // Mock the behavior of dogRepository.findById() method to return an empty Optional
        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> useCase.updateDog(request)); // Assert that IllegalArgumentException is thrown
    }
}
