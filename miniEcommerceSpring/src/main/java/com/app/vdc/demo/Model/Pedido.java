package com.app.vdc.demo.Model;

import javax.persistence.*;
import java.time.LocalTime;


@Entity
public class Pedido {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private LocalTime dataCriacao;
   @ManyToOne(cascade = CascadeType.ALL)
   private User cliente;
   @OneToOne(cascade = CascadeType.ALL)
   private Carrinho car;

   

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalTime getDataCriacao() {
      return dataCriacao;
   }

   public void setDataCriacao(LocalTime dataCriacao) {
      this.dataCriacao = dataCriacao;
   }

   public Carrinho getCar() {
      return car;
   }

   public void setCar(Carrinho car) {
      this.car = car;
   }

   public boolean isIs_finished() {
      return is_finished;
   }

   public void setIs_finished(boolean is_finished) {
      this.is_finished = is_finished;
   }

   public User getCliente() {
      return cliente;
   }

   public void setCliente(User cliente) {
      this.cliente = cliente;
   }

   private boolean is_finished;

   
}
