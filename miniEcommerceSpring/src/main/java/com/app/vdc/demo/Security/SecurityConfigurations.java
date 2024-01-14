package com.app.vdc.demo.Security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// @EnableGlobalMethodSecurity(jsr250Enabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
        
        @Autowired
        private FilterToken filter;
       
        @Bean
        public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
             http.csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeHttpRequests(authorize -> {
               try {
                   authorize
                       .antMatchers(HttpMethod.POST, "/home/cadastroUser").permitAll()
                       .antMatchers(HttpMethod.POST, "/home/login").permitAll()
                       .antMatchers(HttpMethod.GET, "/home/Categoria").hasRole("ADMIN")           
                       .anyRequest().authenticated();
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           });
        return http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class).build();
        }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
            return authenticationConfiguration.getAuthenticationManager();
        }

    @Bean
    PasswordEncoder passwordEncoder(){ 
            return new BCryptPasswordEncoder();
        } 

}
