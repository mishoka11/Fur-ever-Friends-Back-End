package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Implementation.Repositories.DogSizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateDogUseCaseImplementation implements CreateDogUseCase {
    private final DogRepository dogRepository;
    private final DogSizeRepository dogSizeRepository;

    @Override
    public CreateDogResponse createDog(CreateDogRequest request) {
        // Assuming dog years are calculated based on age
        int dogYears = calculateDogYears(request.getAge());

        DogSizeEntity sizeEntity = dogSizeRepository.findBySize(request.getSize())
                .orElseThrow(() -> new IllegalArgumentException("Invalid size"));

        DogEntity dogEntity = DogEntity.builder()
                .name(request.getName())
                .breed(request.getBreed())
                .age(request.getAge())
                .dogYears(dogYears)
                .information(request.getInformation())
                .size(sizeEntity)
                .build();

        dogEntity = dogRepository.save(dogEntity);

        return CreateDogResponse.builder()
                .dogId(dogEntity.getId())
                .build();
    }

    private int calculateDogYears(int age) {
        if (age == 1) {
            return 15;
        } else if (age == 2) {
            return 24;
        } else {
            return 24 + (age - 2) * 4;
        }
    }
}
