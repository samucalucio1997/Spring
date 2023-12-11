package com.app.vdc.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{



     @Bean
     public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
         http.csrf(csrf -> csrf.disable()) //desabilita o csrf (porque eu vou tratar a autenticação dos usuários)
                .authorizeRequests(requests -> requests
                .antMatchers(org.springframework.http.HttpMethod.GET, "/home/cadastroUser").permitAll()
                .antMatchers(org.springframework.http.HttpMethod.GET, "/home/login").permitAll()
                .anyRequest().authenticated());// agora as requisições passíveis de autorização
                
                //na linha 23, está contida a lógica de autenticação definida na classe "MyFilter"
                http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
                return http.build();
                }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        super.configure(auth);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
       return authenticationConfiguration.getAuthenticationManager();
    } 
    @Bean
    public PasswordEncoder passwordEncoder(){
       return passwordEncoder();
    } 
}
