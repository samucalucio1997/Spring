package com.app.vdc.demo.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModalMapperConfig {

    @Bean
    public ModelMapper modalMapper() {
        return new ModelMapper();
    }
}
