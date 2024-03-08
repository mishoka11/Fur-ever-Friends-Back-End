package fontys.s3.Bussiness.Implementation;
import fontys.s3.Bussiness.Implementation.UpdateDogUseCaseImplementation;
import fontys.s3.Domain.UpdateDogRequest;
import fontys.s3.Domain.UpdateDogResponse;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateDogUseCaseImplementationTest {

    @Test
    public void testUpdateDog_ExistingDog() {
        DogRepositoryImplementation mockRepository = Mockito.mock(DogRepositoryImplementation.class);

        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(mockRepository);

        UpdateDogRequest request = new UpdateDogRequest(1L, "Buddy", "Labrador", 5, 1);

        DogEntity dummyDogEntity = new DogEntity(1L, "Max", "German Shepherd", 3, 2);
        when(mockRepository.findById(anyLong())).thenReturn(Optional.of(dummyDogEntity));

        UpdateDogResponse response = useCase.updateDog(request);
        verify(mockRepository).save(argThat(updatedDog ->
                updatedDog.getName().equals(request.getName()) &&
                        updatedDog.getBreed().equals(request.getBreed()) &&
                        updatedDog.getAge() == request.getAge() &&
                        updatedDog.getYears() == request.getYears()
        ));

        assertEquals("Dog updated successfully", response.getMessage());
    }

    @Test
    public void testUpdateDog_NonExistingDog() {
        DogRepositoryImplementation mockRepository = Mockito.mock(DogRepositoryImplementation.class);

        UpdateDogUseCaseImplementation useCase = new UpdateDogUseCaseImplementation(mockRepository);

        UpdateDogRequest request = new UpdateDogRequest(1L, "Buddy", "Labrador", 5, 1);

        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.updateDog(request)
        );
        assertEquals("Dog not found", exception.getMessage());
    }
}
