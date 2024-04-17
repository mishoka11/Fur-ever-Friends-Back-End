package fontys.s3.Bussiness.Implementation;
import fontys.s3.Persistence.Implementation.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteDogUseCaseImplementation implements DeleteDogUseCase {
    private final DogRepository dogRepository;

    @Autowired
    public DeleteDogUseCaseImplementation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public void deleteDog(long dogId) {
        dogRepository.deleteById(dogId);
    }
}