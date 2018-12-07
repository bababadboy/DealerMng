package com.bababadboy.dealermng.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义安全配置
 * @author Ash
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/*").permitAll();
        http.csrf().disable();



    }

    /**
     * 解决swagger-ui.html显示"404
     * @param web weSecurity
     * @throws Exception 异常
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
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
