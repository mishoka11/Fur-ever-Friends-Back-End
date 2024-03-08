package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.DeleteDogUseCase;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import org.springframework.stereotype.Service;

@Service
public class DeleteDogUseCaseImplementation implements DeleteDogUseCase {
    private final DogRepositoryImplementation dogRepository;

    public DeleteDogUseCaseImplementation(DogRepositoryImplementation dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void deleteDog(long dogId) {
        dogRepository.deleteById(dogId);
    }
}