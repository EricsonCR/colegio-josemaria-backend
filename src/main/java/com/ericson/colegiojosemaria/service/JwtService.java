package com.ericson.colegiojosemaria.service;

import com.ericson.colegiojosemaria.interfaces.IJwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService implements IJwt {
    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private static final Long TOKEN_DURACION = 6_000_000L; // 10min en milisegundos

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    @Override
    public String getToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_DURACION))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getUsernameToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
