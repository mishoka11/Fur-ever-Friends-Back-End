package fontys.s3.Domain.UserDomain;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CreateUserRequest {
    private Long userId;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
