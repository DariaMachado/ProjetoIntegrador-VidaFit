package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.pi.model.Produto;

public class ProdutoDao {
	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacao;
	
	private Connection obterConexao() throws SQLException {
		return Conexao.getConnection();
	}
	
	public boolean inserirProduto(Produto modelProduto) throws SQLException {
		String sql = null;
		ResultSet resultSet = null;
		this.estadoOperacao = false;
		long id_gerado = 0;
		this.connection = obterConexao();
		boolean existe = false;
		
		sql = "SELECT * FROM produto WHERE descricao = ? ";
		this.statement = connection.prepareStatement(sql);
		this.statement.setString(1, modelProduto.getNome());

		resultSet = this.statement.executeQuery();

		if (resultSet.next()) {
			existe = true;
		}
		else {
			sql = "INSERT INTO Produto(Descricao, idCategoria, Preco) VALUES (?,?,?)";
			this.statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			this.statement.setString(1, modelProduto.getNome());
			this.statement.setInt(2, modelProduto.getIdCategoria());
			this.statement.setDouble(3, modelProduto.getPreco());
			
			this.estadoOperacao = this.statement.executeUpdate() > 0;

			if (this.estadoOperacao == false) {
				throw new SQLException("Falha na criacao do produto");
			}
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id_gerado = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Falha na criacao do produto! Nao retornou id.");
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
			
			System.out.println("Produto criado com id " + id_gerado);
		}

		
		return existe;
	}
}
