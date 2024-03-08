package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.CreateDogUseCase;
import fontys.s3.Domain.CreateDogRequest;
import fontys.s3.Domain.CreateDogResponse;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateDogUseCaseImplementation implements CreateDogUseCase {
    private final DogRepositoryImplementation dogRepository;

    public CreateDogUseCaseImplementation(DogRepositoryImplementation dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public CreateDogResponse createDog(CreateDogRequest request) {
        DogEntity dogEntity = DogEntity.builder()
                .name(request.getName())
                .breed(request.getBreed())
                .age(request.getAge())
                .years(request.getYears())
                .build();

        dogEntity = dogRepository.save(dogEntity);

        return CreateDogResponse.builder()
                .dogId(dogEntity.getId())
                .build();
    }
}