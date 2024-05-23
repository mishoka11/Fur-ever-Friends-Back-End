package fontys.s3.Configuration_Tests.Authentication;

import fontys.s3.Configuration.security.auth.RequestAuthenticatedUserProvider;
import fontys.s3.Configuration.security.token.AccessToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestAuthenticatedUserProviderTest {

    @InjectMocks
    private RequestAuthenticatedUserProvider provider;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAuthenticatedUserInRequest_shouldReturnAccessToken_whenDetailsAreValid() {
        AccessToken accessToken = new AccessToken() {
            @Override
            public String getSubject() {
                return null;
            }

            @Override
            public Set<String> getRoles() {
                return null;
            }

            @Override
            public boolean hasRole(String roleName) {
                return false;
            }
        };
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(accessToken);
        SecurityContextHolder.setContext(securityContext);

        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNotNull(result);
        assertEquals(accessToken, result);
    }

    @Test
    void getAuthenticatedUserInRequest_shouldReturnNull_whenSecurityContextIsNull() {
        SecurityContextHolder.clearContext();
        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNull(result);
    }

    @Test
    void getAuthenticatedUserInRequest_shouldReturnNull_whenAuthenticationIsNull() {
        when(securityContext.getAuthentication()).thenReturn(null);
        SecurityContextHolder.setContext(securityContext);

        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNull(result);
    }

    @Test
    void getAuthenticatedUserInRequest_shouldReturnNull_whenDetailsAreNotAccessToken() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getDetails()).thenReturn(new Object());
        SecurityContextHolder.setContext(securityContext);

        AccessToken result = provider.getAuthenticatedUserInRequest();

        assertNull(result);
    }
}