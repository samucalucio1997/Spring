package com.app.vdc.demo.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ImagemProduto {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY) 
   private int id;
   
   private String path;


   public String getPath() {
      return path;
   }

   public void setPath(String path) {
      this.path = path;
   }

   public int getId() {
      return id;
   }
}
