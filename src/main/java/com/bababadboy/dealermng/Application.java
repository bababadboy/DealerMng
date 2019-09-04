package com.bababadboy.dealermng;

import com.bababadboy.dealermng.entity.Group;
import com.bababadboy.dealermng.entity.user.Role;
import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

/**
 * @author Ash
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(Application.class, args);
    }


//
//    @Override
//    public void run(String... args) throws Exception {
//        User admin = new User();
//        admin.setUsername("richie");
//        admin.setPassword("richie123");
//        admin.setEmail("richie@gmail.com");
//        admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
//        admin.(new Group("王氏集团有限公司"));
//        userService.groupSignUp(admin);
//    }
}
