package com.bababadboy.dealermng;

import com.bababadboy.dealermng.entity.user.Role;
import com.bababadboy.dealermng.entity.user.User;
import com.bababadboy.dealermng.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application{

//    final UserService userService;
//
//    @Autowired
//    public Application(UserService userService) {
//        this.userService = userService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


//    @Override
//    public void run(String... args) throws Exception {
//        User admin = new User();
//        admin.setUsername("richie");
//        admin.setPassword("richie123");
//        admin.setEmail("richie@gmail.com");
//        admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
//
//        userService.signUp(admin);
//
//        User client = new User();
//        client.setUsername("client");
//        client.setPassword("client123");
//        client.setEmail("client@email.com");
//        client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//
//        userService.signUp(client);
//    }
}
