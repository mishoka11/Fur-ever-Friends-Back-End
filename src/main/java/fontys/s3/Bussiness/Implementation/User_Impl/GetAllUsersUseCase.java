package fontys.s3.Bussiness.Implementation.User_Impl;

import fontys.s3.Domain.UserDomain.GetAllUsersResponse;
import fontys.s3.Domain.UserDomain.GetAllUsersRequest;

public interface GetAllUsersUseCase {
    GetAllUsersResponse getAllUsers(GetAllUsersRequest request);
}