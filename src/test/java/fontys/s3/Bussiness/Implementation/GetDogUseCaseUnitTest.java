package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.GetAllDogsRequest;
import fontys.s3.Domain.GetAllDogsResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @Test
    void testGetAllDogs_WithDogs() {
        MockitoAnnotations.initMocks(this);
        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);

        List<DogEntity> dogs = Arrays.asList(
                new DogEntity(1L, "Fido", "Labrador", 5, 0),
                new DogEntity(2L, "Buddy", "Golden Retriever", 3, 1)
        );

        // Mock the behavior of dogRepository.findAll() method
        when(dogRepository.findAll()).thenReturn(dogs);

        // When
        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        // Then
        assertEquals(2, response.getDogs().size()); // Assert that there are two dogs in the response
    }

    @Test
    void testGetAllDogs_EmptyRepository() {
        MockitoAnnotations.initMocks(this);
        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);

        // Mock the behavior of dogRepository.findAll() to return an empty list
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        // Then
        assertEquals(0, response.getDogs().size()); // Assert that the response is an empty list
    }

    @Test
    void testGetAllDogs_NullRequest() {
        MockitoAnnotations.initMocks(this);
        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);

        // When
        GetAllDogsResponse response = useCase.getAllDogs(null);

        // Then
        assertEquals(0, response.getDogs().size()); // Assert that the response is an empty list
    }

    @Test
    void testGetAllDogs_RepositoryError() {
        MockitoAnnotations.initMocks(this);
        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);

        // Mock the behavior of dogRepository.findAll() method to throw an exception
        when(dogRepository.findAll()).thenThrow(new RuntimeException("Database connection error"));

        // When
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            useCase.getAllDogs(new GetAllDogsRequest());
        });

        // Then
        assertEquals("Database connection error", exception.getMessage());
    }

    @Test
    void testGetAllDogs_NullDogEntities() {
        MockitoAnnotations.initMocks(this);
        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);

        // Mock the behavior of dogRepository.findAll() to return null
        when(dogRepository.findAll()).thenReturn(null);

        // When
        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        // Then
        assertNotNull(response); // Assert that the response is not null
        assertNull(response.getDogs()); // Assert that the list of dogs in the response is null
    }



    @Test
    void testGetAllDogs_EmptyDogEntities() {
        MockitoAnnotations.initMocks(this);
        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);

        // Mock the behavior of dogRepository.findAll() to return an empty list
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        // Then
        assertEquals(0, response.getDogs().size()); // Assert that the response is an empty list
    }
}
