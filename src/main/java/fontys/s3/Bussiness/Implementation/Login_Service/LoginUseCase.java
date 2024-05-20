package fontys.s3.Bussiness.Implementation.Login_Service;

import fontys.s3.Domain.AuthenticationDomain.LoginRequest;
import fontys.s3.Domain.AuthenticationDomain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}