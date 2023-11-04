package net.blog.springbootrestapi.secucrity;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import net.blog.springbootrestapi.exception.BlogAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationTime;

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationTime);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

//        JwtBuilder jwtBuilder = Jwts.builder()
//                .claim("sub", username) // Set subject as a claim directly
//                .claim("iat", currentDate) // Set issued-at as a claim directly
//                .claim("exp", expireDate) // Set expiration as a claim directly
//                .signWith(key());

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // Get username from Jwt Token
    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token.");
        } catch (ExpiredJwtException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token.");
        } catch (UnsupportedJwtException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token.");
        } catch (IllegalArgumentException exception) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty");
        }
    }
}
