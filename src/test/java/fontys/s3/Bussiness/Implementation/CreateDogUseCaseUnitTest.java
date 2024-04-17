package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.CreateDogRequest;
import fontys.s3.Domain.CreateDogResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateDogUseCaseUnitTest {

    @Mock
    private DogRepository dogRepository;

    @Test
    void testCreateDogShouldReturnDog() {
        MockitoAnnotations.initMocks(this);

        // Given
        CreateDogUseCaseImplementation useCase = new CreateDogUseCaseImplementation(dogRepository);
        CreateDogRequest request = new CreateDogRequest("Fido", "Labrador", 5, 0);

        DogEntity savedDogEntity = DogEntity.builder()
                .id(1L)
                .name("Fido")
                .breed("Labrador")
                .age(5)
                .years(0)
                .build();

        // Mock the behavior of dogRepository.save() method
        when(dogRepository.save(any(DogEntity.class))).thenReturn(savedDogEntity);

        // When
        CreateDogResponse response = useCase.createDog(request);

        // Then
        assertEquals(1L, response.getDogId());
    }
}
