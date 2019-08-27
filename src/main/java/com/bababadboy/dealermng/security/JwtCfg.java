package com.bababadboy.dealermng.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JWTfilter配置类
 * @author wangxiaobin
 */
@Configuration
public class JwtCfg {

    @Value("${security.jwt.token.secret-key}")
    private String secretkey;

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addInitParameter(SecurityConstants.SECRETKEY_PARAMETER,secretkey);   // 初始化JwtFilter参数
        registrationBean.addUrlPatterns("/products/*");
        registrationBean.addUrlPatterns("/orders/*");
        registrationBean.addUrlPatterns("/groupwarehouses/*");
        registrationBean.addUrlPatterns("/groupInventory/*");
        registrationBean.addUrlPatterns("/dealers/*");

        return registrationBean;
    }
}
