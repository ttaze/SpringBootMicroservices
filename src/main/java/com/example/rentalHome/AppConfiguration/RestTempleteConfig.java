package com.example.rentalHome.AppConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class RestTempleteConfig extends WebMvcConfigurerAdapter
{
    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
