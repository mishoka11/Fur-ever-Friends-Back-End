package fontys.s3.Bussiness.Dog_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.Dog_Impl.GetAllDogsUseCaseImplementation;
import fontys.s3.Domain.DogDomain.GetAllDogsRequest;
import fontys.s3.Domain.DogDomain.GetAllDogsResponse;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Entity.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    private GetAllDogsUseCaseImplementation useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new GetAllDogsUseCaseImplementation(dogRepository);
    }

    @Test
    void testGetAllDogs_WithDogs() {
        // Given
        DogSizeEntity size = new DogSizeEntity(1L, Size.MEDIUM, null);
        List<DogEntity> dogs = Arrays.asList(
                new DogEntity(1L, "Fido", "Labrador", 5, 0, "good boy", size),
                new DogEntity(2L, "Buddy", "Golden Retriever", 3, 1, "gooder boy", size)
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
        // Given
        // Mock the behavior of dogRepository.findAll() to return an empty list
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        // Then
        assertEquals(0, response.getDogs().size()); // Assert that the response is an empty list
    }

    @Test
    void testGetAllDogs_NullRequest() {
        // When
        GetAllDogsResponse response = useCase.getAllDogs(null);

        // Then
        assertEquals(0, response.getDogs().size()); // Assert that the response is an empty list
    }

    @Test
    void testGetAllDogs_RepositoryError() {
        // Given
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
        // Mock the behavior of dogRepository.findAll() to return an empty list
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        // Then
        assertEquals(0, response.getDogs().size()); // Assert that the response is an empty list
    }
}
