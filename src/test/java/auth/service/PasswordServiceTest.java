package auth.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cms.backend.auth.service.PasswordService;

class PasswordServiceTest {

    private final PasswordService passwordService =
            new PasswordService(
                    new BCryptPasswordEncoder(12));

    @Test
    void shouldHashPassword() {

        String hash =
                passwordService.hashPassword(
                        "1234");

        assertNotEquals(
                "1234",
                hash);
    }

    @Test
    void shouldVerifyPassword() {

        String hash =
                passwordService.hashPassword(
                        "1234");

        assertTrue(
                passwordService.matches(
                        "1234",
                        hash));
    }

    @Test
    void shouldRejectWrongPassword() {

        String hash =
                passwordService.hashPassword(
                        "1234");

        assertFalse(
                passwordService.matches(
                        "5678",
                        hash));
    }
}