package com.FACTor.Digital_Wallet.config;

import com.FACTor.Digital_Wallet.entity.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
@Value("${JWT_SECRET_KEY}")
private String secretString;

private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretString);
    return Keys.hmacShaKeyFor(keyBytes);
}

 public String generateToken(Customer username){
     return Jwts.builder()
             .subject(username.getUsername())
             .issuedAt(new Date())
             .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
             .signWith(getSigningKey())
             .compact();
 }
 public String extractToken(String token) {
     return Jwts.parser()
             .verifyWith(getSigningKey())
             .build()
             .parseSignedClaims(token)
             .getPayload()
             .getSubject();
     }
 public boolean validateToken(String token){
     try {
     Jwts.parser()
             .verifyWith(getSigningKey())
             .build()
             .parseSignedClaims(token);
     return true;
     }catch (Exception e){
         return false;
     }
 }
}
