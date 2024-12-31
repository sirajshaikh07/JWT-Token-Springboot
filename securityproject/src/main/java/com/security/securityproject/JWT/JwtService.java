package com.security.securityproject.JWT;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private String secretkey="";



    // for encoding
    public JwtService()
    {
            try {
                KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSha256");
                SecretKey secretKey=keyGenerator.generateKey();
                secretkey=Base64.getEncoder().encodeToString(secretKey.getEncoded());


            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }

    Map<String,Object>claims=new HashMap<>();

    public String sendToken(String username)
    {
        

        return Jwts.builder().claims().add(claims)
        .subject(username).issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+60*60*30))
        .and().signWith(getKey())
        .compact();


        
    }

    private SecretKey getKey() {
        // TODO Auto-generated method stub
        
        byte[]keybyte=Base64.getDecoder().decode(secretkey);
        return Keys.hmacShaKeyFor(keybyte);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
}
