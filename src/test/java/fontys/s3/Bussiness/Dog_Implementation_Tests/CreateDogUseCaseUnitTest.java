package fontys.s3.Bussiness.Dog_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.Dog_Impl.CreateDogUseCaseImplementation;
import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Entity.Size;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Implementation.Repositories.DogSizeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @Mock
    private DogSizeRepository dogSizeRepository;

    private CreateDogUseCaseImplementation createDogUseCaseImplementation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createDogUseCaseImplementation = new CreateDogUseCaseImplementation(dogRepository, dogSizeRepository);
    }

    @Test
    void testCreateDogShouldReturnDog() {
        DogSizeEntity size = new DogSizeEntity(1L, Size.MEDIUM, null);
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .years(0)
                .information("mlem")
                .size(Size.MEDIUM)
                .build();

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .dogYears(0)
                .information("mlem")
                .size(size)
                .build();

        when(dogSizeRepository.findBySize(Size.MEDIUM)).thenReturn(Optional.of(size));
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        assertEquals(1L, response.getDogId());
    }

    @Test
    void testCreateDogShouldReturnDogId() {
        DogSizeEntity size = new DogSizeEntity(1L, Size.MEDIUM, null);
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .years(0)
                .information("mlem")
                .size(Size.MEDIUM)
                .build();

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .dogYears(0)
                .information("mlem")
                .size(size)
                .build();

        when(dogSizeRepository.findBySize(Size.MEDIUM)).thenReturn(Optional.of(size));
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        assertEquals(1L, response.getDogId());
    }

    @Test
    void testCreateDogWithYearsShouldReturnDogId() {
        DogSizeEntity size = new DogSizeEntity(1L, Size.MEDIUM, null);
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .years(2)
                .information("mlem")
                .size(Size.MEDIUM)
                .build();

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .dogYears(2)
                .information("mlem")
                .size(size)
                .build();

        when(dogSizeRepository.findBySize(Size.MEDIUM)).thenReturn(Optional.of(size));
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        assertEquals(1L, response.getDogId());
    }

    @Test
    void testCreateDogWithZeroAgeAndYearsShouldReturnDogId() {
        DogSizeEntity size = new DogSizeEntity(1L, Size.MEDIUM, null);
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Max")
                .breed("Poodle")
                .age(0)
                .years(0)
                .information("mlem")
                .size(Size.MEDIUM)
                .build();

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Max")
                .breed("Poodle")
                .age(0)
                .dogYears(0)
                .information("mlem")
                .size(size)
                .build();

        when(dogSizeRepository.findBySize(Size.MEDIUM)).thenReturn(Optional.of(size));
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        CreateDogResponse response = createDogUseCaseImplementation.createDog(request);

        assertEquals(1L, response.getDogId());
    }

    @Test
    void testCreateDogWithEmptyNameShouldThrowException() {
        CreateDogRequest request = CreateDogRequest.builder()
                .name("")
                .breed("Labrador")
                .age(5)
                .years(0)
                .information("mlem")
                .size(Size.MEDIUM)
                .build();

        assertThrows(IllegalArgumentException.class, () -> createDogUseCaseImplementation.createDog(request));
    }

    @Test
    void testCreateDogWithNegativeAgeShouldThrowException() {
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Rex")
                .breed("German Shepherd")
                .age(-2)
                .years(0)
                .information("mlem")
                .size(Size.MEDIUM)
                .build();

        assertThrows(IllegalArgumentException.class, () -> createDogUseCaseImplementation.createDog(request));
    }

    @Test
    void testCreateDogWithNullBreedShouldThrowException() {
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Luna")
                .breed(null)
                .age(3)
                .years(0)
                .information("mlem")
                .size(Size.MEDIUM)
                .build();

        assertThrows(IllegalArgumentException.class, () -> createDogUseCaseImplementation.createDog(request));
    }
}
