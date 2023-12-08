package com.app.vdc.demo.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.vdc.demo.Model.User;

public class UserPrincipal implements UserDetails{

    private String username;
    private String password;
    private static List<SimpleGrantedAuthority> authoritieslist ;
    private Collection<? extends GrantedAuthority> Authorities; 

    static{
          authoritieslist.add(new SimpleGrantedAuthority("CadastrarProduto"));
          authoritieslist.add(new SimpleGrantedAuthority("RemoverProduto"));
          authoritieslist.add(new SimpleGrantedAuthority("EditarProduto")); 
        }

    private UserPrincipal(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        if(user.isIs_staff()){
            this.Authorities = authoritieslist;
        }
    }

    public static UserPrincipal create(User user){
        return new UserPrincipal(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return Authorities;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
