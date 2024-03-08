package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.UpdateDogUseCase;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Domain.UpdateDogRequest;
import fontys.s3.Domain.UpdateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDogUseCaseImplementation implements UpdateDogUseCase {
    private final DogRepositoryImplementation dogRepository;

    public UpdateDogUseCaseImplementation(DogRepositoryImplementation dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public UpdateDogResponse updateDog(UpdateDogRequest request) {
        Optional<DogEntity> optionalExistingDog = dogRepository.findById(request.getDogId());
        if (optionalExistingDog.isPresent()) {
            DogEntity existingDog = optionalExistingDog.get();

            existingDog.setName(request.getName());
            existingDog.setBreed(request.getBreed());
            existingDog.setAge(request.getAge());
            existingDog.setYears(request.getYears());

            dogRepository.save(existingDog);

            return UpdateDogResponse.builder()
                    .message("Dog updated successfully")
                    .build();
        } else {
            throw new IllegalArgumentException("Dog not found");
        }
    }
}