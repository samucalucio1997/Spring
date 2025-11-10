package com.app.vdc.demo.repository.custom;

import com.app.vdc.demo.Model.Categorias;
import com.app.vdc.demo.dto.ProdutoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomRepositoryProdutoImp {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Page<ProdutoDTO> filtrarProdutosParametros(String categoria,
                                                      Double precoMin,
                                                      Double precoMax,
                                                      int offset,
                                                      int limit,
                                                      Pageable pageable) {
        String sql = """
                SELECT p.id, p.nome, p.qtd, p.descricao, p.preco_uni, p.categoria AS categoria
                FROM produto p
                WHERE 1=1
                """;
        final var whereclauses = new ArrayList<String>();
        if (!categoria.isBlank()) {
            whereclauses.add(" p.categoria = :categoria");
        }

        if(precoMin != 0){
            whereclauses.add("p.preco_uni >= :precoMin");
        }

        if(precoMax != 0){
            whereclauses.add("p.preco_uni <= :precoMax");
        }

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("categoria", categoria);
        mapSqlParameterSource.addValue("precoMin", precoMin);
        mapSqlParameterSource.addValue("precoMax", precoMax);
        sql += String.join(" AND ", whereclauses);

        if (limit == 0) {
            sql += " LIMIT " + 10;
        }else{
            sql += " LIMIT " + limit;
        }

        if (offset != 0) {
            sql += " OFFSET " + offset*10;
        }

        final var content = jdbcTemplate.query(sql, mapSqlParameterSource, (rs, rowNum) -> {
            return ProdutoDTO.builder()
                    .id(rs.getInt("id"))
                    .categoria(Categorias.valueOf(rs.getString("categoria")))
                    .descricao(rs.getString("descricao"))
                    .qtd(rs.getInt("qtd"))
                    .nome(rs.getString("nome"))
                    .precoUni(rs.getFloat("preco_uni"))
                    .build();
        });
        return new PageImpl<>(content, pageable, content.size());
    }

}
