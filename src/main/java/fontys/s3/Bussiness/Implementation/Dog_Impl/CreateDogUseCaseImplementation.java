package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateDogUseCaseImplementation implements CreateDogUseCase {
    private final DogRepository dogRepository;

    @Override
    public CreateDogResponse createDog(CreateDogRequest request) {
        DogEntity dogEntity = DogEntity.builder()
                .name(request.getName())
                .breed(request.getBreed())
                .age(request.getAge())
                .dogYears(request.getYears())
                .build();

        dogEntity = dogRepository.save(dogEntity);

        return CreateDogResponse.builder()
                .dogId(dogEntity.getId())
                .build();
    }
}
