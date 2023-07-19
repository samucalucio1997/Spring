package com.app.vdc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.vdc.demo.Model.Produto;
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
