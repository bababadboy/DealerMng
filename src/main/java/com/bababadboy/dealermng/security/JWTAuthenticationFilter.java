package com.bababadboy.dealermng.security;

import com.auth0.jwt.JWT;
import com.bababadboy.dealermng.entity.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.bababadboy.dealermng.security.SecurityConstants.EXPIRATION_TIME;
import static com.bababadboy.dealermng.security.SecurityConstants.HEADER_STRING;
import static com.bababadboy.dealermng.security.SecurityConstants.SECRET;
import static com.bababadboy.dealermng.security.SecurityConstants.TOKEN_PREFIX;

/**
 * jwt过滤器，用户认证(authenticating users)
 * @author wangxiaobin
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * parse the user's credentials and issue them to the AuthenticationManager
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {

        try {
            User creds = new ObjectMapper()
                    .readValue(req.getInputStream(),User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * generate a JWT for this user
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth)
            throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

    }
}
