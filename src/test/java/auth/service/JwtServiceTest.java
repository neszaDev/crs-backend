package auth.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.cms.backend.auth.jwt.JwtProperties;
import com.cms.backend.auth.jwt.JwtService;
import com.cms.backend.auth.entity.User;
import com.cms.backend.role.entity.Role;

public class JwtServiceTest {
    @Test
    void shouldGenerateAccessToken() {

        JwtProperties properties = new JwtProperties(
                "1234567890123456789012345678901234567890123456789012345678901234",
                900000,
                604800000);

        JwtService jwtService = new JwtService(properties);

        Role role = new Role();
        role.setName("SUPER_ADMIN");

        User user = new User();
        user.setEmail("admin@admin.com");
        user.setRole(role);

        String token = jwtService.generateAccessToken(user);

        assertNotNull(token);
    }
}
