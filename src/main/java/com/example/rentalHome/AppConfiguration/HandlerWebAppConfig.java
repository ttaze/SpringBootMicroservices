package com.example.rentalHome.AppConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class HandlerWebAppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    HandlerInterceptorConfig handlerInterceptorConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(handlerInterceptorConfig).excludePathPatterns("/user/.*/.*/.*","/user/.*/.*/.*/.*");
    }
}
