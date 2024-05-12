package fontys.s3.Bussiness.Implementation.User_Impl;

import fontys.s3.Domain.UserDomain.GetAllUsersRequest;
import fontys.s3.Domain.UserDomain.GetAllUsersResponse;
import fontys.s3.Domain.UserDomain.User;
import fontys.s3.Persistence.Implementation.Repositories.UserRepository;
import fontys.s3.Persistence.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllUsersUCImpl implements GetAllUsersUseCase {
    private final UserRepository userRepository;

    @Autowired
    public GetAllUsersUCImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GetAllUsersResponse getAllUsers(GetAllUsersRequest request) {
        List<UserEntity> allUsers = userRepository.findAll();

        return GetAllUsersResponse.builder()
                .users(allUsers)
                .build();
    }

    private User convertToUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
