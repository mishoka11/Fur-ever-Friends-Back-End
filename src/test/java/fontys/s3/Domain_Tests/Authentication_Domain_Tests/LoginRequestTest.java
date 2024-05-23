package fontys.s3.Domain_Tests.Authentication_Domain_Tests;

import fontys.s3.Domain.AuthenticationDomain.LoginRequest;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    private final Validator validator;

    LoginRequestTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    void testLoginRequest_Valid() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testLoginRequest_Invalid() {
        LoginRequest loginRequest = LoginRequest.builder()
                .email("")
                .password("")
                .build();

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(loginRequest);
        assertFalse(violations.isEmpty());
        assertEquals(2, violations.size());
    }
}
