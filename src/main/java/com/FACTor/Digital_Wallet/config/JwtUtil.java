package com.FACTor.Digital_Wallet.config;

import com.FACTor.Digital_Wallet.entity.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
 private SecretKey key = Jwts.SIG.HS256.key().build();

 public String generateToken(Customer username){
     return Jwts.builder()
             .subject(username.getUsername())
             .issuedAt(new Date())
             .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
             .signWith(key)
             .compact();
 }
 public String extractToken(String token) {
     return Jwts.parser()
             .verifyWith(key)
             .build()
             .parseSignedClaims(token)
             .getPayload()
             .getSubject();
     }
 public boolean validateToken(String token){
     try {
     Jwts.parser()
             .verifyWith(key)
             .build()
             .parseSignedClaims(token);
     return true;
     }catch (Exception e){
         return false;
     }
 }
}
