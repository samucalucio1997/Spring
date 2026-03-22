package com.app.vdc.demo.Model;

public enum Categorias {
   Roupas("roupas"),
   Calcados("calcados"),
   Eletronicos("eletronicos"),
   Esportes("esportes");

   
   private String descricao;

   private Categorias(String descricao) {
      this.descricao = descricao;
   }


   public String getDescricao() {
      return descricao;
   }
   
   
}
