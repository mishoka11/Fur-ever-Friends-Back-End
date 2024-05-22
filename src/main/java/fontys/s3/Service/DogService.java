package fontys.s3.Service;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Entity.DogSizeEntity;
import fontys.s3.Persistence.Implementation.Repositories.DogRepository;
import fontys.s3.Persistence.Implementation.Repositories.DogSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {

    private final DogRepository dogRepository;
    private final DogSizeRepository dogSizeRepository;

    @Autowired
    public DogService(DogRepository dogRepository, DogSizeRepository dogSizeRepository) {
        this.dogRepository = dogRepository;
        this.dogSizeRepository = dogSizeRepository;
    }

    public CreateDogResponse createDog(CreateDogRequest request) {
        DogSizeEntity sizeEntity = dogSizeRepository.findBySize(request.getSize())
                .orElseThrow(() -> new IllegalArgumentException("Invalid size"));

        DogEntity dogEntity = DogEntity.builder()
                .name(request.getName())
                .breed(request.getBreed())
                .age(request.getAge())
                .size(sizeEntity)
                .information(request.getInformation()) // Updated field name to match the entity
                .build();

        dogEntity = dogRepository.save(dogEntity);

        return CreateDogResponse.builder()
                .dogId(dogEntity.getId())
                .build();
    }

    public Optional<DogEntity> findDogById(long dogId) {
        return dogRepository.findById(dogId);
    }

    public List<DogEntity> findAllDogs() { // Changed method name to match usage in DogController
        return dogRepository.findAll();
    }

    public void deleteDogById(long dogId) {
        dogRepository.deleteById(dogId);
    }
}
