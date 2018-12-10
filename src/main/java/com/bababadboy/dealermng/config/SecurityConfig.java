package com.bababadboy.dealermng.config;


import com.bababadboy.dealermng.security.JWTAuthenticationFilter;
import com.bababadboy.dealermng.security.JWTAuthorizationFilter;
import com.bababadboy.dealermng.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bababadboy.dealermng.security.SecurityConstants.SIGN_UP_URL;

/**
 * 自定义安全配置
 * @author Ash
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 解决Could not autowire. No beans of 'BCryptPasswordEncoder' type found 问题，
    // 加上无参构造
    public SecurityConfig() {
    }

    /**
     * 解决There is no PasswordEncoder mapped for the id “null” 报错
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    /**
     * 控制不同角色可以访问的资源
     * @param http HttpSecurity类
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .antMatchers("/products/**").authenticated()
//                .antMatchers("/api/admin/**").hasRole("ADMIN")  // 管理员权限
//                .and()
//                .formLogin()
//                .and()
//                .logout();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    /**
     * 解决swagger-ui.html显示"404
     * @param web weSecurity
     * @throws Exception 异常
     */
    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers("/v2/api-docs/**", "/configuration/ui", "/swagger.json", "/configuration/security", "/swagger-ui.html", "/webjars/**");

    }
    // @Autowired
    // private UserDetailsService userDetailsService;


    // 指定加密方式
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilter() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                // 设置UserDetailsService
//                .userDetailsService(this.userDetailsService)
//                // 设置passwordEncoder
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                // 由于使用的是JWT，我们这里不需要csrf
//                .csrf().disable()
//
//                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////                .antMatchers("/**").permitAll()       // FOR TEST
//
//                // 允许对于网站静态资源的无授权访问
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/bower_components/**",
//                        "/file/**",
//                        "/styles/**"
//                ).permitAll()
//
//                // 对于获取token的rest api要允许匿名访问
//                .antMatchers("/auth/**").permitAll()
//
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated();
//        // 禁止缓存
//        httpSecurity.headers().cacheControl();
//
//        // 添加JWT filter
//        httpSecurity
//                .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }

}
