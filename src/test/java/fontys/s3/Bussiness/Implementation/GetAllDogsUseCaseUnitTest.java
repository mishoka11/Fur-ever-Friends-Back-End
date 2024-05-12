package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.Dog_Impl.GetAllDogsUseCaseImplementation;
import fontys.s3.Domain.DogDomain.GetAllDogsRequest;
import fontys.s3.Domain.DogDomain.GetAllDogsResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
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
    @Test
    void testGetAllDogs_Successful() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);
        GetAllDogsRequest request = new GetAllDogsRequest();

        // Create some sample dog entities
        List<DogEntity> dogs = Arrays.asList(
                new DogEntity(1L, "Fido", "Labrador", 5, 0),
                new DogEntity(2L, "Buddy", "Golden Retriever", 3, 0)
        );

        // Mock the behavior of dogRepository.findAll() method to return dogs
        when(dogRepository.findAll()).thenReturn(dogs);

        // When
        GetAllDogsResponse response = useCase.getAllDogs(request);

        // Then
        assertEquals(dogs.size(), response.getDogs().size()); // Assert that the number of dogs returned matches the number of dogs in the repository
    }

    @Test
    void testGetAllDogs_EmptyList() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Given
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);
        GetAllDogsRequest request = new GetAllDogsRequest();

        // Mock the behavior of dogRepository.findAll() method to return an empty list
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        GetAllDogsResponse response = useCase.getAllDogs(request);

        // Then
        assertTrue(response.getDogs().isEmpty()); // Assert that the response contains no dogs
    }
    //Unhappy flow tests
    @Test
    void testGetAllDogs_RepositoryError() {
        // Given
        DogRepository dogRepository = mock(DogRepository.class);
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
    void testGetAllDogs_NullRequest() {
        // Given
        DogRepository dogRepository = mock(DogRepository.class); // Mock DogRepository
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository); // Pass mocked DogRepository
        GetAllDogsRequest request = null; // Simulate null request

        // Mock the behavior of dogRepository.findAll() method to return an empty list
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        // Use assertDoesNotThrow to ensure no exception is thrown
        assertDoesNotThrow(() -> useCase.getAllDogs(request));
    }


    @Test
    void testGetAllDogs_EmptyRepository() {
        // Given
        // Create a mocked instance of DogRepository
        DogRepository dogRepository = mock(DogRepository.class);
        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(dogRepository);

        // Mock the behavior of dogRepository.findAll() to return an empty list
        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        // Call getAllDogs() method
        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        // Then
        // Verify that the response is not null and the list of dogs is empty
        assertNotNull(response);
        assertEquals(0, response.getDogs().size());
    }
}
