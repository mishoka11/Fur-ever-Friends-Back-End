package fontys.s3.Bussiness.Implementation.User_Impl;

import fontys.s3.Domain.UserDomain.GetUserResponse;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserUCImpl implements GetUserUseCase {
    private UserRepository userRepository;

    @Autowired
    public void GetUserUseCaseImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GetUserUCImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetUserResponse getUser(long userId) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            return mapToGetUserResponse(userEntity);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    private GetUserResponse mapToGetUserResponse(UserEntity userEntity) {
        return GetUserResponse.builder()
                .userId(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
