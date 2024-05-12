package fontys.s3.Bussiness.Implementation.User_Impl;


import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUCImpl implements CreateUserUseCase.DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUCImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteUser(long userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with ID " + userId + " does not exist");
        }

        userRepository.deleteById(userId);
    }
}
