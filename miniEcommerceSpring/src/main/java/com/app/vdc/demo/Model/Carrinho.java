package com.app.vdc.demo.Model;


import javax.persistence.*;
import java.util.List;


@Entity
public class Carrinho {
	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Produto> produtos;
	@OneToOne(mappedBy = "carrinho",cascade=CascadeType.ALL)
    private User consumidor;

	public void setId(Long id) {
		Id = id;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public User getConsumidor() {
		return consumidor;
	}

	public void setConsumidor(User consumidor) {
		this.consumidor = consumidor;
	}


    public Long getId() {
		return Id;
	}



	
}
