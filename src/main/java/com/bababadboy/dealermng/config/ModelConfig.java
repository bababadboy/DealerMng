package com.bababadboy.dealermng.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注入ModelMapper
 * @author wangxb (O_o)??)
 * @date 2019-09-03
 */
@Configuration
public class ModelConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
