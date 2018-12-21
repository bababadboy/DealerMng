package com.bababadboy.dealermng.service.impl;

import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.exception.CustomException;
import com.bababadboy.dealermng.repository.UserRepository;
import com.bababadboy.dealermng.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangxiaobin
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 用户注册
     * @return 注册成功，返回生成的jwt
     */
    public String signUp(User user) {
        if (!userRepository.existsUserByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println("注册密码是："+user.getPassword());
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * 用户登录
     */
    public String logIn(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("token是"+jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles()));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username or password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    /**
     * 删除注册的用户
     */
    public void delete(String username) {
        userRepository.deleteUserByUsername(username);
    }

    /**
     * 查找用户
     */
    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }


    public User whoami(HttpServletRequest req) {
        return userRepository.
                findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

}
