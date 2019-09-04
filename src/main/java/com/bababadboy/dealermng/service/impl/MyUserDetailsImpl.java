package com.bababadboy.dealermng.service.impl;
import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountLockedException;

/**
 * 获取用户信息
 * @author wangxiaobin
 */
@Service("userDetails")
public class MyUserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    /**
     * 根据用户名查找用户,并用org.springframework.security.core.userdetails.User认证
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名: '"+username+"不存在!!");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(user.isLocked()) // 如果为true 会不会抛出AccountLockedException??
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
