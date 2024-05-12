package fontys.s3.Bussiness.Implementation.User_Impl;

import fontys.s3.Domain.UserDomain.UpdateUserRequest;
import fontys.s3.Domain.UserDomain.UpdateUserResponse;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserUCImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;

    @Autowired
    public UpdateUserUCImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        Optional<UserEntity> optionalExistingUser = userRepository.findById(request.getUserId());
        if (optionalExistingUser.isPresent()) {
            UserEntity existingUser = optionalExistingUser.get();

            existingUser.setUsername(request.getUsername());
            existingUser.setEmail(request.getEmail());
            existingUser.setPassword(request.getPassword());

            userRepository.save(existingUser);

            return UpdateUserResponse.builder()
                    .message("User updated successfully")
                    .build();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
