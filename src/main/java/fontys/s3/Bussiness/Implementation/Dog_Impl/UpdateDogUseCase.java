package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.UpdateDogRequest;
import fontys.s3.Domain.DogDomain.UpdateDogResponse;

public interface UpdateDogUseCase {
    UpdateDogResponse updateDog(UpdateDogRequest request);
}