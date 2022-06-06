package com.pi.model;

import java.util.ArrayList;
import java.util.Date;


public class Pedido {
	private int id;
	private int idCliente;
	private Date data; 
	private ArrayList<Produto> listaProdutos;
	private int IdFormaPagamento;
	private double valor;
	private int IdStatus;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public ArrayList<Produto> getListaProdutos() {
		return listaProdutos;
	}
	public void setListaProdutos(ArrayList<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdFormaPagamento() {
		return IdFormaPagamento;
	}
	public void setIdFormaPagamento(int idFormaPagamento) {
		IdFormaPagamento = idFormaPagamento;
	}
	public int getIdStatus() {
		return IdStatus;
	}
	public void setIdStatus(int idStatus) {
		IdStatus = idStatus;
	}
	
	
	
	
	
}
