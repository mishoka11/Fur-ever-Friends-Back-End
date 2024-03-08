package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.GetDogUseCase;
import fontys.s3.Domain.GetDogResponse;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetDogUseCaseImplementation implements GetDogUseCase {
    private final DogRepositoryImplementation dogRepository;

    public GetDogUseCaseImplementation(DogRepositoryImplementation dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public GetDogResponse getDog(long dogId) {
        Optional<DogEntity> optionalDogEntity = dogRepository.findById(dogId);
        if (optionalDogEntity.isPresent()) {
            DogEntity dogEntity = optionalDogEntity.get();
            return mapToGetDogResponse(dogEntity);
        } else {
            // Handle case where dog is not found
            return null; // Or throw an exception, depending on your requirements
        }
    }

    private GetDogResponse mapToGetDogResponse(DogEntity dogEntity) {
        return GetDogResponse.builder()
                .dogId(dogEntity.getId())
                .name(dogEntity.getName())
                .breed(dogEntity.getBreed())
                .age(dogEntity.getAge())
                .years(dogEntity.getYears())
                .build();
    }
}