package com.bababadboy.dealermng.jwt.service;


import com.bababadboy.dealermng.controller.AuthenticationException;
import com.bababadboy.dealermng.entity.*;
import com.bababadboy.dealermng.exception.CustomException;
import com.bababadboy.dealermng.jwt.util.JwtTokenUtil;
import com.bababadboy.dealermng.repository.AuthorityRepository;
import com.bababadboy.dealermng.repository.DealerRepository;
import com.bababadboy.dealermng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Ash
 * @date 2018/12/22 20:08
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }

    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) loadUserByUsername(username);
        return user;
    }

    public String signUp(User user) {
        if (!userRepository.existsUserByUsername(user.getUsername())) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            Dealer d = user.getDealer();
            Authority authority = new Authority();
            try {
                Date date = new Date();
                d.setRegisterAt(date);
                d.setExpiredAt(new Date(2019, 1, 1));
                d.setCredit(1);
                dealerRepository.save(d);
                List list = new ArrayList<Authority>();
                authority.setName(AuthorityName.ROLE_USER);
                authorityRepository.save(authority);
                list.add(authority);
                user.setAuthorities(list);
                user.setDealer(d);
                user.setEnabled(true);
                user.setLastPasswordResetDate(date);
                userRepository.save(user);
            } catch (Exception e) {
                authorityRepository.deleteById(authority.getId());
                dealerRepository.deleteById(d.getId());
                e.printStackTrace();
                return "";
            }
            UserDetails userDetails = loadUserByUsername(user.getUsername());
            return jwtTokenUtil.generateToken(userDetails);
        } else {
            throw new CustomException("Username is already in use", HttpStatus.BAD_REQUEST);
        }

    }
}