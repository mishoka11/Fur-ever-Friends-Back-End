package fontys.s3.Controller_Tests;

import fontys.s3.Controller.DogController;
import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Entity.Size;
import fontys.s3.Service.DogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DogControllerTest {

    @Mock
    private DogService dogService;

    @InjectMocks
    private DogController dogController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createDog_ShouldReturnCreatedDog_WhenDogIsCreatedSuccessfully() {
        // Arrange
        CreateDogRequest request = CreateDogRequest.builder()
                .name("Buddy")
                .breed("Golden Retriever")
                .age(3)
                .years(21)
                .information("Friendly dog")
                .size(Size.MEDIUM)
                .build();

        CreateDogResponse response = CreateDogResponse.builder()
                .dogId(1L)
                .build();

        when(dogService.createDog(request)).thenReturn(response);

        // Act
        ResponseEntity<CreateDogResponse> result = dogController.createDog(request);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void getAllDogs_ShouldReturnListOfDogs() {
        // Arrange
        DogSizeEntity size = DogSizeEntity.builder()
                .id(1L)
                .size(Size.MEDIUM)
                .build();

        List<DogEntity> dogs = List.of(
                DogEntity.builder().id(1L).name("Buddy").breed("Golden Retriever").age(3).dogYears(21).information("Friendly dog").size(size).build(),
                DogEntity.builder().id(2L).name("Max").breed("Bulldog").age(4).dogYears(28).information("Calm dog").size(size).build()
        );

        when(dogService.findAllDogs()).thenReturn(dogs);

        // Act
        ResponseEntity<List<DogEntity>> response = dogController.getAllDogs();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dogs, response.getBody());
    }
}
