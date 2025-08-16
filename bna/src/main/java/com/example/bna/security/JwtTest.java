import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.nio.charset.StandardCharsets;

public class JwtTest {
    public static void main(String[] args) {
        // Generate or use a 256-bit (32-byte) secret string
        String secretString = "my-super-secret-key-which-is-at-least-32-bytes!";

        // Convert the secret string into a signing key (must be at least 256 bits)
        Key key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

        // Build and sign a JWT token with the key
        String jws = Jwts.builder()
                .setSubject("user123")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("JWT Token: " + jws);

        // To verify the token, parse it using the same key:
        var claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws)
                .getBody();

        System.out.println("Subject from JWT: " + claims.getSubject());
    }
}
