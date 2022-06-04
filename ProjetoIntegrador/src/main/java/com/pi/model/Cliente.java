package com.pi.model;

import java.util.ArrayList;

public class Cliente extends Usuario {
	private ArrayList<Endereco> listaEnderecos;
	private ArrayList<Pedido> listaPedidos;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ArrayList<Endereco> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(ArrayList<Endereco> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public ArrayList<Pedido> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}
	
	
	

}
