package example.com.jwt.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	
	private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud";
	//private final static SecretKey ACCESS_TOKEN_SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS =2_592_000L;
	
	public static  String  createToken(String nombre, String email) {
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
		Date expirationDate = new  Date(System.currentTimeMillis()+ expirationTime);
		
		Map<String, Object> extra = new HashMap<>();
		extra.put("nombre",nombre);
		
		return Jwts.builder()
				   .setSubject(email)
				   .setExpiration(expirationDate)
				   .addClaims(extra)
				   .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
				   //.signWith(ACCESS_TOKEN_SECRET)
				   .compact();
	}
	
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
			
			Claims claims = Jwts.parserBuilder()
								.setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
								//.setSigningKey(ACCESS_TOKEN_SECRET)
								.build()
								.parseClaimsJws(token)
								.getBody();
				String email = claims.getSubject();
				return new  UsernamePasswordAuthenticationToken(email,null, Collections.emptyList());
		} catch (JwtException e) {
			return null;
		}
	}
}

























