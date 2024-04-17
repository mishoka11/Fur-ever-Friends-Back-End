package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.GetAllDogsRequest;
import fontys.s3.Domain.GetAllDogsResponse;
import fontys.s3.Persistence.Implementation.DogRepository;
import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllDogsUseCaseImplementation implements GetAllDogsUseCase {
    private final DogRepository dogRepository;

    @Autowired
    public GetAllDogsUseCaseImplementation(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public GetAllDogsResponse getAllDogs(GetAllDogsRequest request) {
        List<DogEntity> allDogs = dogRepository.findAll();
        return GetAllDogsResponse.builder()
                .dogs(allDogs)
                .build();
    }
}
