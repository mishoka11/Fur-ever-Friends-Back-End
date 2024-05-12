package fontys.s3.Domain.UserDomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
    private Long userId;
    private String username;
    private String email;
    private String password;
}
