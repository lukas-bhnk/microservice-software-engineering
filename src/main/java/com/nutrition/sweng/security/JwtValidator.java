package com.nutrition.sweng.security;

import com.nutrition.sweng.Model.ResourceNotFoundException;
import com.nutrition.sweng.Model.Role;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtValidator {

    @Autowired
    private PublicKeyProvider keys;

/**
     * Retrieves JWT from HTTP-Header 'Authorization'.
     * @param req
     * @return JWT as String without prefix
 * */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


    public boolean isValidJWT(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(keys.getPublicKey()).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new ResourceNotFoundException("JWT invalid");
        }
    }


/**
     * Derives Authentication object from JWT.
     * @param token
     * @return
 */
    public Authentication getAuthentication(String token) {
        return new UsernamePasswordAuthenticationToken(getUserEmail(token), "", getRoles(token));
    }


/**
     * Extracts the user's email from a JWT.
     * @param token
     * @return
*/
    public String getUserEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(keys.getPublicKey()).build().parseClaimsJws(token).getBody().getSubject();
    }


/*
*
     * Extracts the user roles from a JWT.
     * @param token
     * @return
*/
    public Collection<GrantedAuthority> getRoles(String token) {
        Collection<GrantedAuthority> result = new ArrayList<>();
        String temp = Jwts.parserBuilder().setSigningKey(keys.getPublicKey()).build().parseClaimsJws(token).getBody().get("auth", String.class);
        if(temp.equals("NORMAL")){
            result.add(Role.NORMAL);
        }
        else if(temp.equals("PREMIUM")){
            result.add(Role.PREMIUM);
        }
        else if(temp.equals("ADMIN")){
            result.add(Role.ADMIN);
        }
        return result;
    }

}
