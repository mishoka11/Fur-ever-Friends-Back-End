package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.GetAllDogsRequest;
import fontys.s3.Domain.GetAllDogsResponse;

public interface GetAllDogsUseCase {
    GetAllDogsResponse getAllDogs(GetAllDogsRequest request);
}