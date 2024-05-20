package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.GetDogResponse;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetDogUseCaseImplementation implements GetDogUseCase {
    private final DogRepository dogRepository;

    @Override
    public GetDogResponse getDog(long id) {
        DogEntity dogEntity = dogRepository.findById(id).orElse(null);
        if (dogEntity == null) {
            return null;
        }
        return GetDogResponse.builder()
                .dogId(dogEntity.getId())
                .name(dogEntity.getName())
                .breed(dogEntity.getBreed())
                .age(dogEntity.getAge())
                .years(dogEntity.getDogYears())
                .build();
    }
}
