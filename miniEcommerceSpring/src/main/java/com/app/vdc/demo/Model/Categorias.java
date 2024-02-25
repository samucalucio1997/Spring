package com.app.vdc.demo.Model;

public enum Categorias {
   Roupas("roupas"),
   Calcados("calçados"),
   Eletronicos("eletronicos"),
   Esportes("esportes");

   

   private Categorias(String descricao) {
      this.descricao = descricao;
   }

   private String descricao;

   public String getDescricao() {
      return descricao;
   }
   
   
}
