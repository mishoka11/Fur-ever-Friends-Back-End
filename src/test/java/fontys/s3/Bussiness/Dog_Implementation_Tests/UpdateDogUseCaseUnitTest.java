package fontys.s3.Bussiness.Dog_Implementation_Tests;

import fontys.s3.Bussiness.Implementation.Dog_Impl.UpdateDogUseCaseImplementation;
import fontys.s3.Domain.DogDomain.UpdateDogRequest;
import fontys.s3.Domain.DogDomain.UpdateDogResponse;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Entity.Size;
import org.junit.jupiter.api.BeforeEach;
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

    private UpdateDogUseCaseImplementation useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = new UpdateDogUseCaseImplementation(dogRepository);
    }

    @Test
    void testUpdateDogShouldReturnUpdatedDog() {
        long dogId = 1L;
        DogSizeEntity size = new DogSizeEntity(1L, Size.MEDIUM, null);
        UpdateDogRequest request = UpdateDogRequest.builder()
                .dogId(dogId)
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .years(0)
                .information("Info")
                .size(Size.MEDIUM)
                .build();

        DogEntity existingDog = DogEntity.builder()
                .id(dogId)
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .dogYears(0)
                .information("Old Info")
                .size(size)
                .build();

        when(dogRepository.findById(anyLong())).thenReturn(Optional.of(existingDog));

        UpdateDogResponse response = useCase.updateDog(request);

        verify(dogRepository).save(existingDog);
        assertEquals("Dog updated successfully", response.getMessage());
        assertEquals("Fido", existingDog.getName());
        assertEquals("Labrador", existingDog.getBreed());
        assertEquals(5, existingDog.getAge());
        assertEquals("Info", existingDog.getInformation());
        assertEquals(Size.MEDIUM, existingDog.getSize().getSize());
    }

    @Test
    void testUpdateNonExistingDog() {
        long dogId = 1L;
        UpdateDogRequest request = UpdateDogRequest.builder()
                .dogId(dogId)
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .years(0)
                .information("Info")
                .size(Size.MEDIUM)
                .build();

        when(dogRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> useCase.updateDog(request));
    }

    @Test
    void testUpdateDogWithNullRequest() {
        assertThrows(NullPointerException.class, () -> useCase.updateDog(null));
    }
}
