package fontys.s3.Bussiness.Implementation.User_Impl;

import fontys.s3.Domain.UserDomain.User;
import fontys.s3.Persistence.Entity.UserEntity;

final class UserConverter {
    private UserConverter() {
    }

    public static User convert(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .build();
    }
}
