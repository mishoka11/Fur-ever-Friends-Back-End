package fontys.s3.Bussiness.Implementation.User_Impl;


import fontys.s3.Domain.DogDomain.GetDogResponse;
import fontys.s3.Domain.UserDomain.GetUserResponse;

public interface GetUserUseCase {
    GetUserResponse getUser(long userId);
}
