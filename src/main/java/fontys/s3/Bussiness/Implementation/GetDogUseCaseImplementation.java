package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.GetDogResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetDogUseCaseImplementation implements GetDogUseCase {
    private final DogRepository dogRepository;

    @Autowired
    public GetDogUseCaseImplementation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public GetDogResponse getDog(long dogId) {
        Optional<DogEntity> optionalDogEntity = dogRepository.findById(dogId);
        if (optionalDogEntity.isPresent()) {
            DogEntity dogEntity = optionalDogEntity.get();
            return mapToGetDogResponse(dogEntity);
        } else {
            throw new RuntimeException("Dog not found with id: " + dogId);
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
