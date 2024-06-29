package com.app.vdc.demo.Model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nome;
  private int qtd;
  private float precoUni;
  private String descricao;



  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ImagemProduto> imagens;
  @ManyToMany(fetch = FetchType.EAGER)
  @JsonIgnore
  private List<Carrinho> car;
  @Enumerated(EnumType.STRING)
  private Categorias categoria;

  
  
  
public Produto() {
    this.imagens = new ArrayList<>();
  }

public int getId() {
    return id;
  }

public List<ImagemProduto> getImagens() {
    return imagens;
  }

public void setImagens(List<ImagemProduto> imagens) {
    this.imagens = imagens;
  }
  
public List<Carrinho> getCar() {
     return car;
  }
  
public void setCar(List<Carrinho> car) {
    this.car = car;
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

public String getNome() {
  return nome;
}
public void setNome(String nome) {
  this.nome = nome;
}

public String getDescricao() {
    return descricao;
  }

public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}
