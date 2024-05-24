package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteDogUseCaseImplementation {
    private final DogRepository dogRepository;

    public void deleteDog(long dogId) {
        if (dogId <= 0) {
            throw new IllegalArgumentException("Invalid dog ID");
        }

        if (!dogRepository.existsById(dogId)) {
            throw new IllegalArgumentException("Dog not found");
        }

        dogRepository.deleteById(dogId);
    }
}
