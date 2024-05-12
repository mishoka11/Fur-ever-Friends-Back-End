package fontys.s3.Service;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DogService {

    private final DogRepositoryImplementation dogRepository;

    @Autowired
    public DogService(DogRepositoryImplementation dogRepository) {
        this.dogRepository = dogRepository;
    }

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

    public Optional<DogEntity> findDogById(long dogId) {
        return dogRepository.findById(dogId);
    }

    public void deleteDogById(long dogId) {
        dogRepository.deleteById(dogId);
    }
}
