package com.app.vdc.demo.Model;



import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User implements UserDetails{
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 
 private String username;
 private String first_name;
 private String last_name;
 private String email;
 private String password;
 
 
 @OneToOne(cascade=CascadeType.ALL)
 @JsonIgnore
 private Carrinho carrinho;

 private String imagem;

 
 
 private String CEP;
 private int numcasa;
 private boolean is_active;
 private boolean is_staff=false;
 
 
 
 public void setIs_staff(boolean is_staff) {
  this.is_staff = is_staff;
}



public User() {
}



public User(String username, String first_name,
String last_name, String email, 
String password, String cEP,
int numcasa, boolean is_active) {
  this.username = username;
  this.first_name = first_name;
  this.last_name = last_name;
  this.email = email;
  this.password = password;
  this.CEP = cEP;
  this.numcasa = numcasa;
  this.is_active = is_active;
}



public Carrinho getCarrinho() {
  return carrinho;
}

public void setCarrinho(Carrinho carrinho) {
  this.carrinho = carrinho;
}
public int getId() {
  return id;
}

public void setId(int id) {
  this.id = id;
}

public String getUsername() {
  return username;
}

public void setUsername(String username) {
  this.username = username;
  }
  
  public String getFirst_name() {
    return first_name;
  }
  
  
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }
  
  public String getLast_name() {
    return last_name;
}

public void setLast_name(String last_name) {
  this.last_name = last_name;
}

public String getEmail() {
  return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getCEP() {
    return CEP;
  }
  
  public void setCEP(String CEP) {
    this.CEP = CEP;
  }
  

  public String getImagem() {
    return imagem;
  }



  public void setImagem(String imagem) {
    this.imagem = imagem;
  }



  public int getNumcasa() {
   return numcasa;
  }
  
  public void setNumcasa(int numcasa) {
    this.numcasa = numcasa;
  }
  
  public boolean isIs_active() {
    return is_active;
  }
  
  public void setIs_active(boolean is_active) {
    this.is_active = is_active;
  }
  
  public boolean isIs_staff() {
    return is_staff;
  }
  


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
  // TODO Auto-generated method stub
  if(this.is_staff){
    return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
  }else{
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }
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
