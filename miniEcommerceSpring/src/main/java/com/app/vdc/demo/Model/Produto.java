package com.app.vdc.demo.Model;


import java.util.List;

import javax.persistence.*;

@Entity
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nome;
  private int qtd;
  private float precoUni;
  @ManyToMany(fetch = FetchType.EAGER)
  private List<Carrinho> car;
  private Categorias categoria;

public int getId() {
	return id;
}
// public Produto( int qtd, float precoUni, Categorias categoria) {
// 	this.qtd = qtd;
// 	this.precoUni = precoUni;
// 	this.categoria = categoria;
// }

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

public String getNome() {
  return nome;
}
public void setNome(String nome) {
  this.nome = nome;
}

  
}
