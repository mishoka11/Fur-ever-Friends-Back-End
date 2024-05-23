package fontys.s3.Configuration_Tests.Token;


import fontys.s3.Configuration.security.token.impl.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private final String secretKey = "kxJ42AT3AWZrE8/xCab0fmi+BeVcOlZUHJPLQipxEx6J50Arh9ecGtTER8nI//RQsgJW91njGFp+8Wrraw77IA==";

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secret", secretKey);
    }

    @Test
    void testGenerateToken() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtUtil.generateToken(userDetails);
        assertNotNull(token);

        String username = jwtUtil.extractUsername(token);
        assertEquals("testUser", username);
    }

    @Test
    void testValidateToken() {
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");

        String token = jwtUtil.generateToken(userDetails);
        assertTrue(jwtUtil.validateToken(token, userDetails));
    }

    @Test
    void testExtractUsername() {
        String token = createToken("testUser");
        String username = jwtUtil.extractUsername(token);
        assertEquals("testUser", username);
    }

    private String createToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}