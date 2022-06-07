package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.pi.model.Cliente;


public class ClienteDao {
	
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	public List<Cliente> listarClientes() throws SQLException{
		ResultSet resultset = null;
		List<Cliente> listaCliente = new ArrayList<>();
		
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		
		try {
			sql = "SELECT * FROM pessoa ORDER BY nomeCompleto ASC";
			this.statement = this.connection.prepareStatement(sql);
			resultset = this.statement.executeQuery();
			
			while(resultset.next()) {
				Cliente cl = new Cliente();
				cl.setId(resultset.getInt(1));
				cl.setIdTipo(resultset.getInt(2));
				cl.setPrimeiroNome(resultset.getString(3));
				cl.setNomeCompleto(resultset.getString(4));
				cl.setEmail(resultset.getString(5));
				cl.setCpf(resultset.getString(6));
				cl.setRg(resultset.getString(7));
				cl.setTelefone(resultset.getString(8));
				cl.setSenha(resultset.getInt(9));
				
				if(cl.getIdTipo() == 2) {
					listaCliente.add(cl);
				}
				
			}
			this.statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Conex�o fechada");
			this.connection.close();
		}
		
		return listaCliente;
	}
	
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}
}
