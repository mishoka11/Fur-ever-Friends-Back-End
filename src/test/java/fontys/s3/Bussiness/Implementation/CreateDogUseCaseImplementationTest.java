package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.CreateDogUseCaseImplementation;
import fontys.s3.Domain.CreateDogRequest;
import fontys.s3.Domain.CreateDogResponse;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateDogUseCaseImplementationTest {

    @Test
    public void testCreateDog() {
        DogRepositoryImplementation mockRepository = Mockito.mock(DogRepositoryImplementation.class);

        CreateDogUseCaseImplementation useCase = new CreateDogUseCaseImplementation(mockRepository);

        CreateDogRequest request = new CreateDogRequest("Buddy", "Labrador", 5, 1);

        DogEntity savedEntity = new DogEntity();
        savedEntity.setId(1L);
        when(mockRepository.save(any(DogEntity.class))).thenReturn(savedEntity);

        CreateDogResponse response = useCase.createDog(request);

        assertEquals(1L, response.getDogId());
    }
}