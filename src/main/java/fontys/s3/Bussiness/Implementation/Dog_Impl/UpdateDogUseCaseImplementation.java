package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.UpdateDogRequest;
import fontys.s3.Domain.DogDomain.UpdateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateDogUseCaseImplementation implements UpdateDogUseCase {
    private final DogRepository dogRepository;

    @Override
    public UpdateDogResponse updateDog(UpdateDogRequest request) {
        Optional<DogEntity> optionalDog = dogRepository.findById(request.getDogId());

        if (optionalDog.isPresent()) {
            DogEntity dogEntity = optionalDog.get();
            dogEntity.setName(request.getName());
            dogEntity.setBreed(request.getBreed());
            dogEntity.setAge(request.getAge());
            dogEntity.setDogYears(request.getYears());
            dogEntity.setInformation(request.getInformation());
            dogEntity.setSize(new DogSizeEntity(1L, request.getSize(), Collections.singleton(dogEntity)));

            dogRepository.save(dogEntity);

            return UpdateDogResponse.builder()
                    .message("Dog updated successfully")
                    .build();
        } else {
            throw new IllegalArgumentException("Dog not found");
        }
    }
}
