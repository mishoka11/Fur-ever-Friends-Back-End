package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.CreateDogRequest;
import fontys.s3.Domain.DogDomain.CreateDogResponse;

public interface CreateDogUseCase {
    CreateDogResponse createDog(CreateDogRequest request);
}
