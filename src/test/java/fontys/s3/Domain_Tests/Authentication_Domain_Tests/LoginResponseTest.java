package fontys.s3.Domain_Tests.Authentication_Domain_Tests;
import fontys.s3.Domain.AuthenticationDomain.LoginResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void testLoginResponse() {
        LoginResponse loginResponse = LoginResponse.builder()
                .jwt("some-jwt-token")
                .build();

        assertEquals("some-jwt-token", loginResponse.getJwt());
    }
}