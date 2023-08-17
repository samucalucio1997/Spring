package br.com.Pro.exerSB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Pro.exerSB.Models.Produtos;

public interface ProdutoRepo extends JpaRepository<Produtos,Integer>{
    
}
