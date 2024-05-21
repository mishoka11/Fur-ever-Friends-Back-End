package fontys.s3.Service;

import fontys.s3.Bussiness.Exception.UserAlreadyExistsException;
import fontys.s3.Domain.UserDomain.CreateUserRequest;
import fontys.s3.Domain.UserDomain.CreateUserResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import fontys.s3.Persistence.Entity.UserTypeEntity;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Implementation.Repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    public CreateUserResponse createUser(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new UserAlreadyExistsException();
        }

        UserTypeEntity userType = userTypeRepository.findByTypeName("Client"); // Or another logic to set the user type

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // This will be hashed
                .userTypes(Collections.singleton(userType))
                .build();

        UserEntity savedUser = userRepository.save(user);

        return CreateUserResponse.builder()
                .userId(savedUser.getId())
                .build();
    }

    public void updateUser(CreateUserRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }
}
