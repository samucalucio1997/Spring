package br.com.Pro.exerSB.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produtos {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   @Column
   private String nome;
   
   public Produtos() {}

public Produtos(String nome) {
	super();
	this.nome = nome;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}
   
   
}
