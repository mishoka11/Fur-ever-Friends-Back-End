package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.GetDogUseCaseImplementation;
import fontys.s3.Domain.GetDogResponse;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class GetDogUseCaseImplementationTest {

    @Test
    public void testGetDog_ExistingDog() {
        DogRepositoryImplementation mockRepository = Mockito.mock(DogRepositoryImplementation.class);

        GetDogUseCaseImplementation useCase = new GetDogUseCaseImplementation(mockRepository);

        DogEntity dummyDogEntity = new DogEntity(1L, "Buddy", "Labrador", 5, 1);

        when(mockRepository.findById(anyLong())).thenReturn(Optional.of(dummyDogEntity));

        GetDogResponse response = useCase.getDog(1L);

        assertEquals(dummyDogEntity.getId(), response.getDogId());
        assertEquals(dummyDogEntity.getName(), response.getName());
        assertEquals(dummyDogEntity.getBreed(), response.getBreed());
        assertEquals(dummyDogEntity.getAge(), response.getAge());
        assertEquals(dummyDogEntity.getYears(), response.getYears());
    }

    @Test
    public void testGetDog_NonExistingDog() {
        DogRepositoryImplementation mockRepository = Mockito.mock(DogRepositoryImplementation.class);

        GetDogUseCaseImplementation useCase = new GetDogUseCaseImplementation(mockRepository);

        when(mockRepository.findById(anyLong())).thenReturn(Optional.empty());

        GetDogResponse response = useCase.getDog(1L);

        assertEquals(null, response);
    }
}
