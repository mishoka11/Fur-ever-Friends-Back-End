package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.GetAllDogsUseCaseImplementation;
import fontys.s3.Domain.GetAllDogsRequest;
import fontys.s3.Domain.GetAllDogsResponse;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetAllDogsUseCaseImplementationTest {

    @Test
    public void testGetAllDogs() {
        DogRepositoryImplementation mockRepository = Mockito.mock(DogRepositoryImplementation.class);

        GetAllDogsUseCaseImplementation useCase = new GetAllDogsUseCaseImplementation(mockRepository);

        List<DogEntity> dummyDogList = new ArrayList<>();
        dummyDogList.add(new DogEntity(1L, "Buddy", "Labrador", 5, 1));
        dummyDogList.add(new DogEntity(2L, "Max", "German Shepherd", 3, 2));
        dummyDogList.add(new DogEntity(3L, "Lucy", "Golden Retriever", 4, 1));

        when(mockRepository.findAll()).thenReturn(dummyDogList);

        GetAllDogsRequest request = new GetAllDogsRequest();

        GetAllDogsResponse response = useCase.getAllDogs(request);

        assertEquals(dummyDogList, response.getDogs());
    }
}