package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pi.model.Pedido;



public class PedidoDao {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	public List<Pedido> listarPedidoCliente(int Id_usuario) throws SQLException {
		ResultSet resultSet = null;
		List<Pedido> arrayPedido = new ArrayList<>();

		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();

		try {
			sql = "SELECT * FROM pedido WHERE idPessoa=?";
			this.statement = this.connection.prepareStatement(sql);
			this.statement.setInt(1, Id_usuario);

			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Pedido ped = new Pedido();
				ped.setId(resultSet.getInt(1));
				ped.setIdStatus(resultSet.getInt(2));
				ped.setData(resultSet.getDate(3));
				ped.setIdCliente(resultSet.getInt(4));
				ped.setIdFormaPagamento(resultSet.getInt(5));
				
				arrayPedido.add(ped);

			}

			this.statement.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Conex�o fechada");
			this.connection.close();
		}

		return arrayPedido;
	}
	
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}
}
