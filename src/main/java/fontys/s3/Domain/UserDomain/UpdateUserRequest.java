package fontys.s3.Domain.UserDomain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @Setter
    private long userId;
    private String username;
    private String email;
    private String password;

    public void setId(long id) {
    }
}
