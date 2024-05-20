package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.Dog_Impl.CreateDogUseCaseImplementation;
import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @InjectMocks
    private CreateDogUseCaseImplementation createDogUseCaseImplementation;

    // Happy flow tests
    @Test
    void testCreateDogShouldReturnDog() {
        // Given
        CreateDogRequest request = new CreateDogRequest("Fido", "Labrador", 5, 0);

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .dogYears(0)
                .build();

        // Mock the behavior of dogRepository.save() method
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        // When
        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        // Then
        assertEquals(1L, response.getDogId());
    }

    @Test
    void testCreateDogShouldReturnDogId() {
        // Given
        CreateDogRequest request = new CreateDogRequest("Fido", "Labrador", 5, 0);

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .dogYears(0)
                .build();

        // Mock the behavior of dogRepository.save() method
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        // When
        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        // Then
        assertEquals(1L, response.getDogId());
    }

    @Test
    void testCreateDogWithYearsShouldReturnDogId() {
        // Given
        CreateDogRequest request = new CreateDogRequest("Buddy", "Golden Retriever", 3, 2);

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .dogYears(2)
                .build();

        // Mock the behavior of dogRepository.save() method
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        // When
        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        // Then
        assertEquals(1L, response.getDogId());
    }

    @Test
    void testCreateDogWithZeroAgeAndYearsShouldReturnDogId() {
        // Given
        CreateDogRequest request = new CreateDogRequest("Max", "Poodle", 0, 0);

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Max")
                .breed("Poodle")
                .age(0)
                .dogYears(0)
                .build();

        // Mock the behavior of dogRepository.save() method
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        // When
        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        // Then
        assertEquals(1L, response.getDogId());
    }

    // Unhappy flow tests
    @Test
    void testCreateDogWithEmptyNameShouldThrowException() {
        // Given
        CreateDogRequest request = new CreateDogRequest("", "Labrador", 5, 0);

        // When / Then
        assertThrows(NullPointerException.class, () -> createDogUseCaseImplementation.createDog(request));
    }

    @Test
    void testCreateDogWithNegativeAgeShouldThrowException() {
        // Given
        CreateDogRequest request = new CreateDogRequest("Rex", "German Shepherd", -2, 0);

        // When / Then
        assertThrows(NullPointerException.class, () -> createDogUseCaseImplementation.createDog(request));
    }

    @Test
    void testCreateDogWithNullBreedShouldThrowException() {
        // Given
        CreateDogRequest request = new CreateDogRequest("Luna", null, 3, 0);

        // When / Then
        assertThrows(NullPointerException.class, () -> createDogUseCaseImplementation.createDog(request));
    }
}
