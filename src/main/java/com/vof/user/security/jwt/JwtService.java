package com.vof.user.security.jwt;

import com.vof.user.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Service
public class JwtService {

    private static String SECRET="ClaveDeCodificacionSuperSeguraParaElToken";



    public static String obtenerSubject(String tokenJWT) {
        try {
            // Convertir la clave secreta a un objeto SecretKey
            SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

            // Decodificar el token JWT
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(key).build().parseSignedClaims(tokenJWT);

            // Obtener el sujeto del token JWT
            String subject = jwsClaims.getBody().getSubject();

            return subject;
        } catch (Exception e) {
            // Manejar errores de decodificación del token
            e.printStackTrace();
            return null;
        }
    }
    public String generateToken( UserDetails userEntity) {
        return Jwts.builder()
                .setSubject(userEntity.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //-->   Token expira en 10 horas   <--
                .setExpiration(new Date(System.currentTimeMillis() + 1000* 60 * 60 *10))
                .signWith(getKeySecret())
                .compact();
    }
    private Key getKeySecret(){
        //-->   Utilizo para su generacion HMAC-SHA   <--
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
}
