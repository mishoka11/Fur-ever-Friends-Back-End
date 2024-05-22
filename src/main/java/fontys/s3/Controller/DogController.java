package fontys.s3.Controller;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogs")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class DogController {

    private final DogService dogService;

    @PostMapping
    public ResponseEntity<CreateDogResponse> createDog(@RequestBody CreateDogRequest request) {
        CreateDogResponse dog = dogService.createDog(request);
        return ResponseEntity.ok(dog);
    }

    @GetMapping
    public ResponseEntity<List<DogEntity>> getAllDogs() {
        List<DogEntity> dogs = dogService.findAllDogs();
        return ResponseEntity.ok(dogs);
    }
}
