package fontys.s3.Configuration_Tests.Token;


import fontys.s3.Configuration.security.token.impl.AccessTokenImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccessTokenImplTest {

    @Test
    void testAccessTokenImpl() {
        AccessTokenImpl accessToken = new AccessTokenImpl("testSubject", List.of("ROLE_USER"));
        assertEquals("testSubject", accessToken.getSubject());
        assertTrue(accessToken.hasRole("ROLE_USER"));
        assertFalse(accessToken.hasRole("ROLE_ADMIN"));
    }
}
