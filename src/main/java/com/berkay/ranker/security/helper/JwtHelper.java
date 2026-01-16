package com.berkay.ranker.security.helper;

import com.berkay.ranker.security.model.JwtProperties;
import com.berkay.ranker.user.data.entity.UserToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtHelper {

    private final JwtProperties jwtProperties;

    public String generateToken(String username) {
        final Date now = new Date();
        final Date expiryDate = new Date(now.getTime() + jwtProperties.getExpirationTime());

        final Map<String, Object> claims = new HashMap<>();
        claims.put("type", "LOGIN_TOKEN");

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey())
                .compact();
    }

    public Claims extractAllClaims(final String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(final String token) {
        return extractAllClaims(token).getSubject();
    }

    public <T> T extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String getTokenHeader() {
        return jwtProperties.getTokenHeader();
    }

    public Boolean isTokenExpired(final String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean checkTokenExpired(final UserToken userToken) {
        boolean isTokenExpired = false;

        if (Objects.nonNull(userToken)) {
            try {
                isTokenExpired(userToken.getToken());
            } catch (final Exception exception) {
                isTokenExpired = true;
            }
        } else {
            isTokenExpired = true;
        }
        return isTokenExpired;
    }
}
