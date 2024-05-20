package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.UpdateDogRequest;
import fontys.s3.Domain.DogDomain.UpdateDogResponse;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateDogUseCaseImplementation implements UpdateDogUseCase {
    private final DogRepository dogRepository;

    @Override
    public UpdateDogResponse updateDog(UpdateDogRequest request) {
        if (request == null) {
            throw new NullPointerException("UpdateDogRequest cannot be null");
        }

        Optional<DogEntity> optionalDogEntity = dogRepository.findById(request.getDogId());
        if (!optionalDogEntity.isPresent()) {
            throw new IllegalArgumentException("Dog not found");
        }

        DogEntity existingDog = optionalDogEntity.get();
        existingDog.setName(request.getName());
        existingDog.setBreed(request.getBreed());
        existingDog.setAge(request.getAge());
        existingDog.setDogYears(request.getYears());

        dogRepository.save(existingDog);

        return UpdateDogResponse.builder()
                .message("Dog updated successfully")
                .build();
    }
}
