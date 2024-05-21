package fontys.s3.Bussiness.Implementation.Dog_Impl;
import fontys.s3.Domain.DogDomain.GetDogResponse;

public interface GetDogUseCase {
    GetDogResponse getDog(long id);
}