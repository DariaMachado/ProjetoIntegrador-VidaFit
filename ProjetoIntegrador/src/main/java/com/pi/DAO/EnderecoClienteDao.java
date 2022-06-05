package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.pi.model.Endereco;



public class EnderecoClienteDao {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;

	public boolean inserirEnderecoCliente(Endereco endereco) throws SQLException {
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();

		try {
			// this.connection.setAutoCommit(false);
			sql = "INSERT INTO endereco(idPessoa, estado, cidade, cep, rua, numero, complemento) VALUES (?,?,?,?,?,?,?)";
			this.statement = this.connection.prepareStatement(sql);

			this.statement.setInt(1, endereco.getIdCliente());
			this.statement.setString(2, endereco.getEstado());
			this.statement.setString(3, endereco.getCidade());
			this.statement.setString(4, endereco.getCep());
			this.statement.setString(5, endereco.getRua());
			this.statement.setInt(6, endereco.getNumero());
			this.statement.setString(7, endereco.getComplemento());
			this.estadoOperacao = this.statement.executeUpdate() > 0;

			this.connection.commit();
			this.statement.close();
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		} finally {
			System.out.println("Fechando!");
			this.connection.close();
		}

		return estadoOperacao;
		// TODO Auto-generated method stub

	}

	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}
}
