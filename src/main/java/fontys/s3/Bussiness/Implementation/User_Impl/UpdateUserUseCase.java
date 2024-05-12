package fontys.s3.Bussiness.Implementation.User_Impl;


import fontys.s3.Domain.UserDomain.UpdateUserRequest;
import fontys.s3.Domain.UserDomain.UpdateUserResponse;

public interface UpdateUserUseCase {
    UpdateUserResponse updateUser(UpdateUserRequest request);
}
