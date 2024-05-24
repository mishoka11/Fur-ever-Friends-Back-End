package fontys.s3.Configuration_Tests.Authentication;

import fontys.s3.Configuration.security.auth.AuthenticationRequestFilter;
import fontys.s3.Configuration.security.token.impl.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthenticationRequestFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private AuthenticationRequestFilter filter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain chain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext(); // Clear the security context before each test
    }

    @Test
    void doFilter_shouldNotAuthenticateUser_whenAuthorizationHeaderIsMissing() throws ServletException, IOException {
        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilter_shouldNotAuthenticateUser_whenTokenIsInvalid() throws ServletException, IOException {
        String token = "Bearer invalid-token";

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtUtil.extractUsername(anyString())).thenThrow(new IllegalArgumentException());

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilter_shouldNotAuthenticateUser_whenTokenIsExpired() throws ServletException, IOException {
        String token = "Bearer expired-token";

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtUtil.extractUsername(anyString())).thenThrow(new ExpiredJwtException(null, null, "Token expired"));

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilter_shouldAuthenticateUser_whenTokenIsValid() throws ServletException, IOException {
        String token = "Bearer valid-token";
        String username = "user";
        UserDetails userDetails = mock(UserDetails.class);

        when(request.getHeader("Authorization")).thenReturn(token);
        when(jwtUtil.extractUsername(anyString())).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtil.validateToken(anyString(), any(UserDetails.class))).thenReturn(true);

        filter.doFilter(request, response, chain);

        verify(jwtUtil).extractUsername(anyString());
        verify(chain).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }
}
