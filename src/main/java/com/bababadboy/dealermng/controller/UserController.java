package com.bababadboy.dealermng.controller;

import com.bababadboy.dealermng.repository.ApplicationUserRepository;
import com.bababadboy.dealermng.user.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxiaobin
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private  ApplicationUserRepository applicationUserRepository;

    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println("用户密码是："+user.getPassword());
        user.setPassword(user.getPassword());

        applicationUserRepository.save(user);
    }



}
