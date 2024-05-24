package fontys.s3.Domain_Tests.User_Domain_Tests;


import fontys.s3.Domain.UserDomain.GetAllUsersResponse;
import fontys.s3.Persistence.Entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetAllUsersResponseTest {

    @Test
    void testGetAllUsersResponse() {
        List<UserEntity> userList = List.of(
                UserEntity.builder().id(1L).username("User1").build(),
                UserEntity.builder().id(2L).username("User2").build()
        );
        GetAllUsersResponse response = GetAllUsersResponse.builder().users(userList).build();

        assertEquals(userList, response.getUsers());
    }
}