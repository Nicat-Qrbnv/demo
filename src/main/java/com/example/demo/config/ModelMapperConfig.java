package com.example.demo.config;

import com.example.demo.model.Course;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(String.class, Course.class)
                .addMapping(src -> src, Course::setTitle);
        return new ModelMapper();
    }
}