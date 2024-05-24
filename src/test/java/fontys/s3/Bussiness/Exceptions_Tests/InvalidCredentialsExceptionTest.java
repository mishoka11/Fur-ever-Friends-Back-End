package fontys.s3.Bussiness.Exceptions_Tests;


import fontys.s3.Bussiness.Exception.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCredentialsExceptionTest {

    @Test
    void testInvalidCredentialsException_DefaultConstructor() {
        InvalidCredentialsException exception = new InvalidCredentialsException();

        assertNotNull(exception);
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("INVALID_CREDENTIALS", exception.getReason());
    }
}