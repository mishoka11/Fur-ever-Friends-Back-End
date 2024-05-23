package fontys.s3.Configuration_Tests.Token;


import fontys.s3.Configuration.security.token.AccessToken;

import fontys.s3.Configuration.security.token.AccessToken;
import fontys.s3.Configuration.security.token.exception.InvalidAccessTokenException;
import fontys.s3.Configuration.security.token.impl.AccessTokenEncoderDecoderImpl;
import fontys.s3.Configuration.security.token.impl.AccessTokenImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.Key;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenEncoderDecoderImplTest {

    private AccessTokenEncoderDecoderImpl encoderDecoder;
    private final String secretKey = "kxJ42AT3AWZrE8/xCab0fmi+BeVcOlZUHJPLQipxEx6J50Arh9ecGtTER8nI//RQsgJW91njGFp+8Wrraw77IA==";

    @BeforeEach
    void setUp() {
        encoderDecoder = new AccessTokenEncoderDecoderImpl(secretKey);
    }

    @Test
    void testEncodeDecode() {
        AccessToken accessToken = new AccessTokenImpl("testSubject", List.of("ROLE_USER"));
        String encodedToken = encoderDecoder.encode(accessToken);
        assertNotNull(encodedToken);

        AccessToken decodedToken = encoderDecoder.decode(encodedToken);
        assertEquals(accessToken.getSubject(), decodedToken.getSubject());
        assertEquals(accessToken.getRoles(), decodedToken.getRoles());
    }

    @Test
    void testDecodeInvalidToken() {
        String invalidToken = "invalidToken";
        assertThrows(InvalidAccessTokenException.class, () -> encoderDecoder.decode(invalidToken));
    }
}