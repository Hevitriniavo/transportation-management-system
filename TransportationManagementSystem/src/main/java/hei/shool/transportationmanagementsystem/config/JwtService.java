package hei.shool.transportationmanagementsystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    private <T> T extractClaim(String token, Function<Claims, T> claimsDecoder){
        Claims claims = extractAllClaims(token);
     return claimsDecoder.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(userDetails, new HashMap<>());
    }


    public  boolean isTokenValid(String token, UserDetails userDetails){
        String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpire(token));
    }

    private boolean isTokenExpire(String token) {
      return extractExpirationTime(token).before(new Date());
    }

    private Date extractExpirationTime(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public String generateToken(UserDetails userDetails, Map<String, String> extraClaims){
      return Jwts
              .builder()
              .setClaims(extraClaims)
              .setSubject(userDetails.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 24))
              .signWith(getSigningKey(), SignatureAlgorithm.HS256)
              .compact();
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(this.secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
