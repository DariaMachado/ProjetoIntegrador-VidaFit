package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pi.model.Usuario;

public class UsuarioDao {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	
	public Usuario login(Usuario user) throws SQLException {
		ResultSet resultSet = null;

		String sql = null;
		
		Usuario usuario = new Usuario();

		this.estadoOperacao = false;

		this.connection = obterConexao();

		try {

			sql = "SELECT * FROM pessoa WHERE email = ? AND senha=?";
			this.statement = connection.prepareStatement(sql);
			this.statement.setString(1, user.getEmail());
			this.statement.setInt(2, user.getSenha());

			resultSet = this.statement.executeQuery();

			if (resultSet.next()) {	
				usuario.setEmail(resultSet.getString(1));
				usuario.setSenha(resultSet.getInt(2));
				usuario.setIdTipo(resultSet.getInt(3));
				
			}
			this.statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Ok fechou a conexao!");
			this.connection.close();
		}

		return usuario;
	}

	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}
}
