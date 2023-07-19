package com.app.vdc.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.vdc.demo.Model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
      

}
