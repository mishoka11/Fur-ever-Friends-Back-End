package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteDogUseCaseImplementation implements DeleteDogUseCase {
    private final DogRepository dogRepository;

    @Override
    public void deleteDog(long dogId) {
        dogRepository.deleteById(dogId);
    }
}