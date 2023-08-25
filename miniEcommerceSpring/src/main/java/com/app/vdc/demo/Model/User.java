package com.app.vdc.demo.Model;


import javax.persistence.*;


@Entity
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 private String username;
 private String first_name;
 
 private String last_name;
 
 private String email;
 
 private String password;

 @OneToOne(cascade=CascadeType.ALL)
 private Carrinho carrinho;
 private String CEP;

 private int numcasa;

 private boolean is_active;

private final boolean is_staff=false;



 public User(String username, String first_name, 
 String last_name, String email, String password,
         String cEP, int numcasa, boolean is_active) {
    this.username = username;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.password = password;
    CEP = cEP;
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




}
