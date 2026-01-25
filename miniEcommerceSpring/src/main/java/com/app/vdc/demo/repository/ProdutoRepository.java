package com.app.vdc.demo.repository;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.dto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.vdc.demo.Model.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

      <T> Collection<T> findByNome(String nome);

      @Query("SELECT p " +
             "FROM Produto p " +
             "WHERE 1=1 AND (:categoria IS NULL OR p.categoria = :categoria) " +
             "AND (:precoMin IS NULL OR p.precoUni >= :precoMin) " +
             "AND (:precoMax IS NULL OR p.precoUni <= :precoMax) "
      )
      Page<Produto> findByFilter(@Param("categoria") Categorias categoria,
                                 @Param("precoMin") Float precoMin,
                                 @Param("precoMax") Float precoMax, Pageable pageable);
}
