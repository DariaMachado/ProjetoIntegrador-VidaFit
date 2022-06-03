package com.pi.model;

import java.util.ArrayList;

public class Administrador extends Usuario {
	private String cnpj;
	private ArrayList<Cliente> listaClientes;
	
	
	public Administrador() {
		super();
		
	}
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	
}
