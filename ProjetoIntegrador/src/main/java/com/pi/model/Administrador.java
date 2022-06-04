package com.pi.model;

import java.util.ArrayList;

public class Administrador extends Usuario {
	private ArrayList<Cliente> listaClientes;
	
	
	public Administrador() {
		super();
		
	}
	


	public ArrayList<Cliente> getListaClientes() {
		return listaClientes;
	}
	public void setListaClientes(ArrayList<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	
}
