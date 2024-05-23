package fontys.s3.Domain_Tests.Authentication_Domain_Tests;

import fontys.s3.Domain.AuthenticationDomain.SignUpRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignUpRequestTest {

    @Test
    void testSignUpRequest() {
        SignUpRequest signUpRequest = new SignUpRequest("username", "test@example.com", "password");

        assertEquals("username", signUpRequest.getUsername());
        assertEquals("test@example.com", signUpRequest.getEmail());
        assertEquals("password", signUpRequest.getPassword());
    }
}