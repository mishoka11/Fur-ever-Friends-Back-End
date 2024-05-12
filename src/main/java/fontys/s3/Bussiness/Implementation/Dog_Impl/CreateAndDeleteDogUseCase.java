package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;
import fontys.s3.Persistence.Entity.DogEntity;
import fontys.s3.Persistence.Implementation.DogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public interface CreateAndDeleteDogUseCase {
    CreateDogResponse createDog(CreateDogRequest request);

    @Service
    @AllArgsConstructor
    class CreateDogUseCaseImplementation implements CreateAndDeleteDogUseCase {

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

    interface DeleteDogUseCase {
        void deleteDog(long dogId);
    }
}

