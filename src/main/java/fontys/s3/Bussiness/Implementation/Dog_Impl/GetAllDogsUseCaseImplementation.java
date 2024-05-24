package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.Dog;
import fontys.s3.Domain.DogDomain.GetAllDogsRequest;
import fontys.s3.Domain.DogDomain.GetAllDogsResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllDogsUseCaseImplementation implements GetAllDogsUseCase {
    private final DogRepository dogRepository;

    @Override
    public GetAllDogsResponse getAllDogs(GetAllDogsRequest request) {
        List<DogEntity> dogs = dogRepository.findAll();
        if (dogs == null) {
            return GetAllDogsResponse.builder().dogs(null).build();
        }
        List<Dog> dogResponses = dogs.stream()
                .map(dog -> Dog.builder()
                        .id(dog.getId())
                        .name(dog.getName())
                        .breed(dog.getBreed())
                        .age(dog.getAge())
                        .dogYears(dog.getDogYears())
                        .build())
                .collect(Collectors.toList());
        return GetAllDogsResponse.builder()
                .dogs(dogResponses)
                .build();
    }
}
