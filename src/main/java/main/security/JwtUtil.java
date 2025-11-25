/**
 * Utilidad para generación y validación de tokens JWT.
 * <p>
 * Proporciona métodos para crear, validar y extraer información de tokens JWT usando una clave secreta.
 * <br>
 * Utility class for JWT token generation and validation. Provides methods to create, validate and extract
 * information from JWT tokens using a secret key.
 * <p>
 * Autor / Author: Fernando Cote
 * Fecha / Date: 2025-11-24
 */
package main.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // 1. Generar token JWT
    /**
     * Genera un token JWT para el usuario especificado.
     * <br>
     * Generates a JWT token for the specified user.
     *
     * @param username nombre de usuario / username
     * @return token JWT generado / generated JWT token
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 2. Extraer el username del token
    /**
     * Extrae el nombre de usuario del token JWT.
     * <br>
     * Extracts the username from the JWT token.
     *
     * @param token token JWT / JWT token
     * @return nombre de usuario extraído / extracted username
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Valida si el token JWT es válido y corresponde al usuario.
     * <br>
     * Validates if the JWT token is valid and matches the user.
     *
     * @param token token JWT / JWT token
     * @param username nombre de usuario / username
     * @return true si es válido, false en caso contrario / true if valid, false otherwise
     */
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expirationDate.before(new Date());
    }

}
