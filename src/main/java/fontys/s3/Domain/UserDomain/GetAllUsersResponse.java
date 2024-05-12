package fontys.s3.Domain.UserDomain;


import fontys.s3.Persistence.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersResponse {
    private List<UserEntity> users;
}
