package com.bababadboy.dealermng.service.impl;
import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 权限控制用户service
 * @author wangxiaobin
 */
@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    /**
     * 根据用户名查找用户,并用org.springframework.security.core.userdetails.User认证
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User: '"+username+"' not found!!");
        }
        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(user.getPassword())//
                .authorities(user.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
