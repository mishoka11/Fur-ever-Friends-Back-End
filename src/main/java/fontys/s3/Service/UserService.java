package fontys.s3.Service;

import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        UserEntity userEntity = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        userEntity = userRepository.save(userEntity);

        return CreateUserResponse.builder()
                .userId(userEntity.getId())
                .build();
    }

    public Optional<UserEntity> findUserById(long userId) {
        return userRepository.findById(userId);
    }

    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }
}