package fontys.s3.Bussiness.Implementation.Login_Service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
