package fontys.s3.Bussiness.Implementation.User_Impl;

import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);

    @Service
    @AllArgsConstructor
    class CreateUserUseCaseImplementation implements CreateUserUseCase {

        private final UserRepository userRepository;

        @Override
        public CreateUserResponse createUser(CreateUserRequest request) {
            UserEntity userEntity = UserEntity.builder()
                    .username(request.getUsername())
                    .email(request.getEmail()) // Ensure this line matches the field in UserEntity
                    .password(request.getPassword())
                    .build();

            UserEntity savedUser = userRepository.save(userEntity);

            return CreateUserResponse.builder()
                    .userId(savedUser.getId())
                    .build();
        }
    }

    interface DeleteUserUseCase {
        void deleteUser(long userId);
    }
}
