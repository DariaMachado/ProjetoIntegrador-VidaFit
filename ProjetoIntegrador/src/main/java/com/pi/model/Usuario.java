package com.pi.model;

public class Usuario {
	private int id;
	private int idTipo;
	private String nome;
	private String cpf;
	private String rg;
	private String telefone;
	private String email;
	private int senha;
	

	public Usuario() {
		super();
		
	}
	
	


	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getIdTipo() {
		return idTipo;
	}


	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getSenha() {
		return senha;
	}


	public void setSenha(int senha) {
		this.senha = senha;
	}
	
	
	
}
