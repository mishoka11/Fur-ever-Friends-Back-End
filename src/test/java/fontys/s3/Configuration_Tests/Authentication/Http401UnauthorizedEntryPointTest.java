package fontys.s3.Configuration_Tests.Authentication;


import fontys.s3.Configuration.security.auth.Http401UnauthorizedEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import static org.mockito.Mockito.verify;

class Http401UnauthorizedEntryPointTest {

    @InjectMocks
    private Http401UnauthorizedEntryPoint entryPoint;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private AuthenticationException authException;

    @Test
    void commence_shouldSend401Error() throws IOException {
        MockitoAnnotations.openMocks(this);

        entryPoint.commence(request, response, authException);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}