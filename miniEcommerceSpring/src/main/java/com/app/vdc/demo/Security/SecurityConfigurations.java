package com.app.vdc.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfigurations {

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
            return authenticationConfiguration.getAuthenticationManager();
        } 
        
        @Bean
        public PasswordEncoder passwordEncoder(){ 
            return new BCryptPasswordEncoder();
        } 

        @Bean
        public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
             http.csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests(authorize -> {
               try {
                   authorize
                       .antMatchers(HttpMethod.POST, "/home/cadastroUser").permitAll()
                       .antMatchers(HttpMethod.GET, "/home/login").permitAll()
                       .anyRequest().authenticated();
               } catch (Exception e) {
                   throw new RuntimeException(e);
               }
           });
           return http.build();
           //  .antMatchers(HttpMethod.POST, "/home/cadastroUser")
           //  .permitAll()
           //  .antMatchers(HttpMethod.GET, "/home/login")
           //  .permitAll()
           //  .anyRequest().authenticated();
            //  http.csrf(csrf -> csrf.disable()) //desabilita o csrf (porque eu vou tratar a autenticação dos usuários)
            //             .authorizeRequests(requests -> {
       //  http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
       //                 try {
       //                     requests
       //                     .antMatchers(org.springframework.http.HttpMethod.GET, "/home/cadastroUser").permitAll()
       //                     .antMatchers(org.springframework.http.HttpMethod.GET, "/home/login").permitAll()
       //                     .anyRequest().authenticated().and().build();
       //                 } catch (Exception e) {
       //                     // TODO Auto-generated catch block
       //                     e.printStackTrace();
       //                 }
       //             }).build();// agora as requisições passíveis de autorização
                   //na linha 23, está contida a lógica de autenticação definida na classe "MyFilter"
                  
        }
}
