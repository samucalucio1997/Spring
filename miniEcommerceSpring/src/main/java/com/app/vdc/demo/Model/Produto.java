package com.app.vdc.demo.Model;


import javax.persistence.*;

@Entity
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int qtd;
  @OneToOne(fetch = FetchType.EAGER)
  private User Consumidor;
  private float precoUni;
  private Categorias categoria;
  public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getQtd() {
	return qtd;
}
public void setQtd(int qtd) {
	this.qtd = qtd;
}
public Categorias getCategoria() {
	return categoria;
}
public void setCategoria(Categorias categoria) {
	this.categoria = categoria;
}
public float getPrecoUni() {
	return precoUni;
}
public void setPrecoUni(float precoUni) {
	this.precoUni = precoUni;
}
  
  
}
