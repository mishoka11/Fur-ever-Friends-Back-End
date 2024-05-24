package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Implementation.Repositories.DogSizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
public class CreateDogUseCaseImplementation {
    private final DogRepository dogRepository;
    private final DogSizeRepository dogSizeRepository;

    public CreateDogResponse createDog(CreateDogRequest request) {
        validateRequest(request);

        DogSizeEntity sizeEntity = dogSizeRepository.findBySize(request.getSize())
                .orElseThrow(() -> new IllegalArgumentException("Invalid size"));

        DogEntity dogEntity = DogEntity.builder()
                .name(request.getName())
                .breed(request.getBreed())
                .age(request.getAge())
                .dogYears(request.getYears())
                .information(request.getInformation())
                .size(sizeEntity)
                .build();

        DogEntity savedDogEntity = dogRepository.save(dogEntity);

        return CreateDogResponse.builder()
                .dogId(savedDogEntity.getId())
                .build();
    }

    private void validateRequest(CreateDogRequest request) {
        if (!StringUtils.hasText(request.getName())) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (request.getAge() < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        if (request.getBreed() == null) {
            throw new IllegalArgumentException("Breed cannot be null");
        }
    }
}
