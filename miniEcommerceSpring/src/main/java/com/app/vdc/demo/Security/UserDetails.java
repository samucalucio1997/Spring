package com.app.vdc.demo.Security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.UserRepository;
import com.app.vdc.demo.services.UserService;

public class UserDetails implements UserDetailsService{
    @Autowired
    UserRepository Consumer;
   
    @Autowired
    UserService userService;
    
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        User user = Consumer.findAll().stream().filter(n->n.isIs_staff()).findFirst().get();
        if(user!=null){
           throw new RuntimeException("Usuario já existe");
        }
        return UserPrincipal.create(user);
    }
  
}
