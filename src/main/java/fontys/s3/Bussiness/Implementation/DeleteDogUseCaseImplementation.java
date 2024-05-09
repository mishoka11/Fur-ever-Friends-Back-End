package fontys.s3.Bussiness.Implementation;
import fontys.s3.Domain.Dog;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Implementation.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteDogUseCaseImplementation implements DeleteDogUseCase {
    private final DogRepository dogRepository;

    @Autowired
    public DeleteDogUseCaseImplementation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void deleteDog(long dogId) {
        Optional<DogEntity> dog = dogRepository.findById(dogId);
        if (dog.isEmpty()) {
            throw new IllegalArgumentException("Dog with ID " + dogId + " does not exist");
        }
        dogRepository.deleteById(dogId);
    }
}