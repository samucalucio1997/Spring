package com.app.vdc.demo.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Perfil local do usuario. A identidade e as credenciais ficam no Keycloak; aqui
 * guardamos apenas os dados de negocio, ligados ao {@code sub} do token pelo campo
 * {@code keycloakId}.
 */
@Entity
@Table(name = "usuario")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "keycloak_id", unique = true)
	private String keycloakId;

	private String username;

	private String first_name;

	private String last_name;

	private String email;

	private String idCostumer;

	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Carrinho carrinho;

	private String imagem;

	private String CEP;

	private int numcasa;

	private boolean is_active;

	public User() {
	}

	public User(String keycloakId, String username, String first_name, String last_name, String email, String cEP,
			int numcasa, boolean is_active) {
		this.keycloakId = keycloakId;
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.CEP = cEP;
		this.numcasa = numcasa;
		this.is_active = is_active;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeycloakId() {
		return keycloakId;
	}

	public void setKeycloakId(String keycloakId) {
		this.keycloakId = keycloakId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String CEP) {
		this.CEP = CEP;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public int getNumcasa() {
		return numcasa;
	}

	public void setNumcasa(int numcasa) {
		this.numcasa = numcasa;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public String getIdCostumer() {
		return idCostumer;
	}

	public void setIdCostumer(String idCostumer) {
		this.idCostumer = idCostumer;
	}

}
