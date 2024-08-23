package com.saurabh.social_connect.jwts;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    static final String key = "ba5acad332482216be7b2d3c13a479fd64f3c2cbb505a38652796dbfbfaf9d36";

    /*
        Extracts Email from JWT
     */
     String extractUsernameFromJWT(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken( Map<String, Object> extractClaims, UserDetails userDetails) {
         return Jwts.builder().claims(extractClaims)
                 .subject(userDetails.getUsername())
                 .issuedAt(new Date(System.currentTimeMillis()))
                 .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                 .signWith(getSignInKey())
                 .compact();
    }

    public String generateToken(UserDetails userDetails) {
         return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isValidToken(String jwtToken, UserDetails userDetails) {
         final String userName = extractUsernameFromJWT(jwtToken);
         return (userName.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {

         return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
         return extractClaim(jwtToken, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
         final Claims claims = extractAllClaims(token);
         return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
         return Jwts.parser().verifyWith((SecretKey) getSignInKey())
                 .build().parseSignedClaims(jwtToken).getPayload();
    }

    private Key getSignInKey() {
         return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(key));
    }

}
