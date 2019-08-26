package com.bababadboy.dealermng.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * 从Http请求中提取JWT的信息，并使用了”secretkey”这个密匙对JWT进行验证
 * @author wangxiaobin
 */
public class JwtFilter extends GenericFilterBean {

    // todo filter无法注入 https://blog.csdn.net/itguangit/article/details/78349033
//    @Value("${security.jwt.token.secret-key}")
    private String secretkey;

    @PostConstruct
    protected void init() {
        // 转成base64编码
        secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());
    }

    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
        // 获取JwtCfg中设置的初始参数
        secretkey = super.getFilterConfig().getInitParameter("secretkey");
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        // Change the req and res to HttpServletRequest and HttpServletResponse
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        // Get authorization from Http request
        final String authHeader = request.getHeader("authorization");

        // If the Http request is OPTIONS then just return the status code 200
        // which is HttpServletResponse.SC_OK in this code
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {

            // Check the authorization, check if the token is started by "Bearer "
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }

            // Then get the JWT token from authorization
            final String token = authHeader.substring(7);

            try {
                // Use JWT parser to check if the signature is valid with the Key "secret-key"
                // 使用了”secretkey”这个密匙对JWT进行验证
                final Claims claims = Jwts
                        .parser()
                        .setSigningKey(Base64.getEncoder().encodeToString(secretkey.getBytes()))
                        .parseClaimsJws(token).getBody();

                // Add the claim to request header
                request.setAttribute("claims", claims);
            } catch (final SignatureException e) {
                throw new ServletException("Invalid token");
            }

            chain.doFilter(req, res);
        }
    }
}