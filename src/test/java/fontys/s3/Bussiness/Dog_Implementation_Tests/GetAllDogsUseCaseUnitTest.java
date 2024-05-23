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
import static org.mockito.Mockito.when;

class GetAllDogsUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    private GetAllDogsUseCaseImplementation useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new GetAllDogsUseCaseImplementation(dogRepository);
    }

    @Test
    void testGetAllDogs_Successful() {
        GetAllDogsRequest request = new GetAllDogsRequest();
        DogSizeEntity size = new DogSizeEntity(1L, Size.MEDIUM, Collections.emptySet());
        List<DogEntity> dogs = Arrays.asList(
                new DogEntity(1L, "Fido", "Labrador", 5, 0, "Info", size),
                new DogEntity(2L, "Buddy", "Golden Retriever", 3, 0, "Info", size)
        );

        when(dogRepository.findAll()).thenReturn(dogs);

        GetAllDogsResponse response = useCase.getAllDogs(request);

        assertEquals(dogs.size(), response.getDogs().size());
        assertEquals("Fido", response.getDogs().get(0).getName());
        assertEquals("Buddy", response.getDogs().get(1).getName());
    }

    @Test
    void testGetAllDogs_EmptyList() {
        GetAllDogsRequest request = new GetAllDogsRequest();

        when(dogRepository.findAll()).thenReturn(Collections.emptyList());

        GetAllDogsResponse response = useCase.getAllDogs(request);

        assertTrue(response.getDogs().isEmpty());
    }

    @Test
    void testGetAllDogs_NullDogEntities() {
        when(dogRepository.findAll()).thenReturn(null);

        GetAllDogsResponse response = useCase.getAllDogs(new GetAllDogsRequest());

        assertNotNull(response);
        assertNull(response.getDogs());
    }

    @Test
    void testGetAllDogs_RepositoryError() {
        when(dogRepository.findAll()).thenThrow(new RuntimeException("Database connection error"));

        Throwable exception = assertThrows(RuntimeException.class, () -> {
            useCase.getAllDogs(new GetAllDogsRequest());
        });

        assertEquals("Database connection error", exception.getMessage());
    }
}
