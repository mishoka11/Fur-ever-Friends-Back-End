package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.UpdateDogRequest;
import fontys.s3.Domain.UpdateDogResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    void testUpdateDogWithNullRequest() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(dogRepository);

        // When
        // Then
        assertThrows(NullPointerException.class, () -> useCase.updateDog(null)); // Assert that NullPointerException is thrown
    }

    @Test
    void testUpdateDogWithNullExistingDog() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(dogRepository);
        long dogId = 1L;
        UpdateDogRequest request = new UpdateDogRequest(dogId, "Fido", "Labrador", 5, 0);

        // Mock the behavior of dogRepository.findById() method to return null
        when(dogRepository.findById(anyLong())).thenReturn(null);

        // When
        // Then
        assertThrows(NullPointerException.class, () -> useCase.updateDog(request)); // Assert that NullPointerException is thrown
    }

    @Test
    void testUpdateDogWithEmptyName() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(dogRepository);
        long dogId = 1L;
        UpdateDogRequest request = new UpdateDogRequest(dogId, "", "Labrador", 5, 0);

        DogEntity existingDog = new DogEntity(dogId, "Buddy", "Golden Retriever", 3, 0);

        // Mock the behavior of dogRepository.findById() method
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(existingDog));

        // When
        UpdateDogResponse response = useCase.updateDog(request);

        // Then
        verify(dogRepository).save(existingDog); // Verify that dogRepository.save() is called with the existingDog
        assertEquals("Dog updated successfully", response.getMessage()); // Assert the success message
        assertEquals("", existingDog.getName()); // Assert that the name of the existing dog is updated to empty
    }

    @Test
    void testUpdateDogWithNegativeAge() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(dogRepository);
        long dogId = 1L;
        UpdateDogRequest request = new UpdateDogRequest(dogId, "Fido", "Labrador", -5, 0);

        DogEntity existingDog = new DogEntity(dogId, "Buddy", "Golden Retriever", 3, 0);

        // Mock the behavior of dogRepository.findById() method
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(existingDog));

        // When
        UpdateDogResponse response = useCase.updateDog(request);

        // Then
        verify(dogRepository).save(existingDog); // Verify that dogRepository.save() is called with the existingDog
        assertEquals("Dog updated successfully", response.getMessage()); // Assert the success message
        assertEquals(-5, existingDog.getAge()); // Assert that the age of the existing dog is updated to negative
    }

    @Test
    void testUpdateDogWithInvalidYears() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Given
        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(dogRepository);
        long dogId = 1L;
        UpdateDogRequest request = new UpdateDogRequest(dogId, "Fido", "Labrador", 5, -1);

        DogEntity existingDog = new DogEntity(dogId, "Buddy", "Golden Retriever", 3, 0);

        // Mock the behavior of dogRepository.findById() method
        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(existingDog));

        // When
        UpdateDogResponse response = useCase.updateDog(request);

        // Then
        verify(dogRepository).save(existingDog); // Verify that dogRepository.save() is called with the existingDog
        assertEquals("Dog updated successfully", response.getMessage()); // Assert the success message
        assertEquals(-1, existingDog.getYears()); // Assert that the years of the existing dog is updated to negative
    }
}
