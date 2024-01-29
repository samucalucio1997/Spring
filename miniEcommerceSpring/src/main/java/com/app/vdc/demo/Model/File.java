package com.app.vdc.demo.Model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;


@Entity
public class File{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private UUID id;
    private String Nome;
    private String NomeOriginal;
    private String path;
    
    
    
    public File() {
    }
    
public File(String path) {
        this.path = path;
    }

public String getPath() {
    return path;
}

public void setPath(String path) {
    this.path = path;
}

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}
public String getNome() {
    return Nome;
}

public void setNome(String nome) {
    Nome = nome;
}

public String getNomeOriginal() {
    return NomeOriginal;
}

public void setNomeOriginal(String nomeOriginal) {
    NomeOriginal = nomeOriginal;
}   
}
