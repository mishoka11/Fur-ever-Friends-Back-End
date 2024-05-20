package fontys.s3.Bussiness.Implementation.Login_Service;

import fontys.s3.Bussiness.Exception.InvalidCredentialsException;
import fontys.s3.Configuration.security.token.AccessTokenEncoder;
import fontys.s3.Configuration.security.token.impl.AccessTokenImpl;
import fontys.s3.Domain.AuthenticationDomain.LoginRequest;
import fontys.s3.Domain.AuthenticationDomain.LoginResponse;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Entity.UserTypeEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!matchesPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        List<String> roles = user.getUserTypes().stream()
                .map(UserTypeEntity::getTypeName)
                .toList();

        return accessTokenEncoder.encode(
                new AccessTokenImpl(user.getUsername(), roles));
    }
}
