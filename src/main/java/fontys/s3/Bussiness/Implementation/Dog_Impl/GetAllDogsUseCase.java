package fontys.s3.Bussiness.Implementation.Dog_Impl;

import fontys.s3.Domain.DogDomain.GetAllDogsRequest;
import fontys.s3.Domain.DogDomain.GetAllDogsResponse;

public interface GetAllDogsUseCase {
    GetAllDogsResponse getAllDogs(GetAllDogsRequest request);
}