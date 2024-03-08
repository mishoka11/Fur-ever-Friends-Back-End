package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.DeleteDogUseCaseImplementation;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

public class DeleteDogUseCaseImplementationTest {

    @Test
    public void testDeleteDog() {
        DogRepositoryImplementation mockRepository = Mockito.mock(DogRepositoryImplementation.class);

        DeleteDogUseCaseImplementation useCase = new DeleteDogUseCaseImplementation(mockRepository);

        long dogIdToDelete = 1L;
        useCase.deleteDog(dogIdToDelete);

        verify(mockRepository).deleteById(dogIdToDelete);
    }
}