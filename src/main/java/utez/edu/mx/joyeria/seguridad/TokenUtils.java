package utez.edu.mx.joyeria.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET= "OJBDshbdasljoBILHVBF45df_OJNSBFSFSF654fsf"; //esto es la firma del token ****DEBE CAMBIARSE****
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS=2592800L;//tiempo de valdes en segundos (30 dias)

    public static String createToken(String nombre,String email){
        long expirationTime=ACCESS_TOKEN_VALIDITY_SECONDS*1000;//tiempo de expiracion en milisegundos
        Date expirationDate=new Date(System.currentTimeMillis()+expirationTime);

        Map<String,Object> extra= new HashMap<>();
        extra.put("nombre:",nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentification(String token){

        try{
            Claims claims=Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email= claims.getSubject();//obtenemos el email

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

        }catch (JwtException e){
            return null;
        }


    }


}
