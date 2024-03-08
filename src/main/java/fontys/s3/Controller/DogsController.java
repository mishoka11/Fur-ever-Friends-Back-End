package fontys.s3.Controller;

import fontys.s3.Bussiness.Implementation.*;
import fontys.s3.Domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
public class DogsController {
    private final GetDogUseCase getDogUseCase;
    private final GetAllDogsUseCase GetAllDogsUseCase;
    private final DeleteDogUseCase deleteDogUseCase;
    private final CreateDogUseCase createDogUseCase;
    private final UpdateDogUseCase updateDogUseCase;
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDog(@PathVariable("id") long id) {
        final GetDogResponse dogResponse = getDogUseCase.getDog(id);

        if (dogResponse != null) {
            return ResponseEntity.ok().body(mapToDog(dogResponse));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Dog mapToDog(GetDogResponse dogResponse) {
        return Dog.builder()
                .id(dogResponse.getDogId())
                .name(dogResponse.getName())
                .breed(dogResponse.getBreed())
                .age(dogResponse.getAge())
                .years(dogResponse.getYears())
                .build();
    }
    @GetMapping
    public ResponseEntity<GetAllDogsResponse> getDogs() {
        return ResponseEntity.ok(GetAllDogsUseCase.getAllDogs(GetAllDogsRequest.builder().build()));
    }
    @DeleteMapping("/{dogId}")
    public ResponseEntity<Void> deleteDog(@PathVariable("dogId") long dogId) {
        deleteDogUseCase.deleteDog(dogId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CreateDogResponse> createDog(@RequestBody @Valid CreateDogRequest request) {
        CreateDogResponse response = createDogUseCase.createDog(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDog(@PathVariable("id") long id, @RequestBody @Valid UpdateDogRequest request) {
        request.setId(id);
        updateDogUseCase.updateDog(request);
        return ResponseEntity.noContent().build();
    }

}