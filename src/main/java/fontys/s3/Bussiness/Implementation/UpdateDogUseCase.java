package fontys.s3.Bussiness.Implementation;

import fontys.s3.Domain.UpdateDogRequest;
import fontys.s3.Domain.UpdateDogResponse;

public interface UpdateDogUseCase {
    UpdateDogResponse updateDog(UpdateDogRequest request);
}