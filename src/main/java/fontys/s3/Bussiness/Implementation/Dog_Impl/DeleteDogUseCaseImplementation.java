package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Persistence.Implementation.DogRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteDogUseCaseImplementation implements CreateAndDeleteDogUseCase.DeleteDogUseCase {

    private DogRepository dogRepository;

    public DeleteDogUseCaseImplementation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void deleteDog(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Dog ID must be positive");
        }

        if (!dogRepository.existsById(id)) {
            throw new IllegalArgumentException("Dog with ID " + id + " does not exist");
        }

        dogRepository.deleteById(id);
    }
}
