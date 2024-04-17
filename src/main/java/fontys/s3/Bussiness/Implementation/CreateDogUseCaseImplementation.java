package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.CreateDogRequest;
import fontys.s3.Domain.CreateDogResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
                .years(request.getYears())
                .build();

        DogEntity dogEntity1 = dogRepository.save(dogEntity);

        return CreateDogResponse.builder()
                .dogId(dogEntity1.getId())
                .build();
    }
}
