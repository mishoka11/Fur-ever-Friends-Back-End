package fontys.s3.Bussiness.Implementation;

import fontys.s3.Bussiness.Implementation.GetAllDogsUseCase;
import fontys.s3.Domain.GetAllDogsRequest;
import fontys.s3.Domain.GetAllDogsResponse;
import fontys.s3.Persistence.Implementation.DogRepositoryImplementation;
import fontys.s3.Persistence.Entity.DogEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllDogsUseCaseImplementation implements GetAllDogsUseCase {
    private final DogRepositoryImplementation dogRepository;

    public GetAllDogsUseCaseImplementation(DogRepositoryImplementation dogRepository) {
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