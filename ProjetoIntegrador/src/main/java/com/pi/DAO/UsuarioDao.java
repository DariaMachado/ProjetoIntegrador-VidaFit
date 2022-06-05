package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
				usuario.setId(resultSet.getInt(1));
				usuario.setIdTipo(resultSet.getInt(2));
				usuario.setPrimeiroNome(resultSet.getString(3));
				usuario.setNomeCompleto(resultSet.getString(4));
				usuario.setCpf(resultSet.getString(5));
				usuario.setRg(resultSet.getString(6));
				usuario.setTelefone(resultSet.getString(7));
				usuario.setEmail(resultSet.getString(8));
				usuario.setSenha(resultSet.getInt(9));
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
	
	public long inserirUsuario(Usuario usuario) throws SQLException {
		String sql = null;
		long id_gerado = 0;
		this.estadoOperacao = false;
		this.connection = obterConexao();

		this.connection.setAutoCommit(false);
		sql = "INSERT INTO pessoa(idFuncao, primeiroNome, nomeCompleto, email, cpf, rg, telefone, senha) VALUES (?,?,?,?,?,?,?,?)";
		this.statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		//this.statement.setString(1, null);
		this.statement.setInt(1, usuario.getIdTipo());
		this.statement.setString(2, usuario.getPrimeiroNome());
		this.statement.setString(3, usuario.getNomeCompleto());
		this.statement.setString(4, usuario.getEmail());
		this.statement.setString(5, usuario.getCpf());
		this.statement.setString(6, usuario.getRg());
		this.statement.setString(7, usuario.getTelefone());
		this.statement.setInt(8, usuario.getSenha());

		this.estadoOperacao = statement.executeUpdate() > 0;

		if (this.estadoOperacao == false) {
			throw new SQLException("Falha na criacao do usuario");
		}
		try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				id_gerado = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Falha na criacao do usuario! Nao retornou id.");
			}

			this.connection.commit();
			this.statement.close();
		} catch (SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		} finally {
			System.out.println("Fechou a conexao!");
			this.connection.close();
		}

		return id_gerado;
	}
	
	
}


