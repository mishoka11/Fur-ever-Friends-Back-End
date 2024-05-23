package fontys.s3.Bussiness.Dog_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.Dog_Impl.DeleteDogUseCaseImplementation;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    private DeleteDogUseCaseImplementation useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new DeleteDogUseCaseImplementation(dogRepository);
    }

    @Test
    void testDeleteDogWithNegativeIdShouldThrowException() {
        long negativeDogId = -1L;

        assertThrows(IllegalArgumentException.class, () -> useCase.deleteDog(negativeDogId));
    }

    @Test
    void testDeleteDogWithZeroIdShouldThrowException() {
        long zeroDogId = 0L;

        assertThrows(IllegalArgumentException.class, () -> useCase.deleteDog(zeroDogId));
    }

    @Test
    void testDeleteNonExistingDogShouldThrowException() {
        long nonExistingDogId = 99L; // Non-existing ID

        when(dogRepository.existsById(nonExistingDogId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> useCase.deleteDog(nonExistingDogId));
    }
}
