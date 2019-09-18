package com.TCP.messaging.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCreator {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
