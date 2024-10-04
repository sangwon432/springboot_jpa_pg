package com.sangwon.springboot_jpa_pg.security;

import com.sangwon.springboot_jpa_pg.exceptions.UserApiException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;



@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
//    @Value("daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
//    @Value("604800000")
    private long jwtExpirationDate;

    // jwt 생성
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .signWith(key())
                .compact();
        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from jwt
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate jwt
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException malformedJwtException) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException expiredJwtException) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "Illegal JWT token");
        }
    }
}
