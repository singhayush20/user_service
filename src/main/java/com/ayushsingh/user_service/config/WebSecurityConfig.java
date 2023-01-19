package com.ayushsingh.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //for security on methods (hasRole, hasSecurity, etc)
public class WebSecurityConfig{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        security.authorizeHttpRequests()
            .anyRequest()
            .authenticated()
            .and()
            //configure resource server
            .oauth2ResourceServer()
            .jwt();
        return security.build();
    }
}