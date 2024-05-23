package fontys.s3.Bussiness.Implementation.User_Impl;

import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (request.getEmail() == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        if (request.getPassword() == null || request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters long");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        UserEntity savedUserEntity = userRepository.save(userEntity);

        return CreateUserResponse.builder()
                .userId(savedUserEntity.getId())
                .build();
    }
}
