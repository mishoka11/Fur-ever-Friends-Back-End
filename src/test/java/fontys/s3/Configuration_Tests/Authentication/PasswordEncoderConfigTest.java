package fontys.s3.Configuration_Tests.Authentication;

import fontys.s3.Configuration.security.auth.PasswordEncoderConfig;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordEncoderConfigTest {

    @Test
    void passwordEncoder_shouldReturnBCryptPasswordEncoderInstance() {
        PasswordEncoderConfig config = new PasswordEncoderConfig();
        PasswordEncoder encoder = config.passwordEncoder();

        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }
}