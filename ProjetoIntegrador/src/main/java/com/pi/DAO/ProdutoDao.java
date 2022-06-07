package com.pi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.pi.model.Categoria;
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
	
	public List<Produto> listarProd() throws SQLException{
		ResultSet resultset = null;
		List<Produto> listaProduto = new ArrayList<>();
		
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		
		try {
			sql = "SELECT * FROM produto ORDER BY idCategoria";
			this.statement = this.connection.prepareStatement(sql);
			resultset = this.statement.executeQuery();
			
			while(resultset.next()) {
				Produto p = new Produto();
				p.setId(resultset.getInt(1));
				p.setNome(resultset.getString(2));
				p.setIdCategoria(resultset.getInt(3));
				p.setPreco(resultset.getDouble(4));
				
				listaProduto.add(p);
			}
			this.statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Conexao fechada");
			this.connection.close();
		}
		
		
		
		return listaProduto;
	}
	
	
	public Produto listarProduto(int id_produto) throws SQLException {
		ResultSet resultSet = null;
		Produto prod = new Produto();

		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();

		try {
			sql = "SELECT * FROM produto WHERE id =?";
			statement=connection.prepareStatement(sql);
			statement.setInt(1, id_produto);
			
			resultSet = statement.executeQuery();
			
			if(resultSet.next()) {				
				prod.setId(resultSet.getInt(1));
				prod.setNome(resultSet.getString(2));
				prod.setIdCategoria(resultSet.getInt(3));
				prod.setPreco(Double.parseDouble(resultSet.getString(4)));
			}
			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			System.out.println("fechou");
			connection.close();
		}

		return prod;
	}
	
	public boolean alterarProduto(Produto prod) throws SQLException {
		String sql = null;
		this.estadoOperacao = false;
		this.connection = obterConexao();
		
		try {
		this.connection.setAutoCommit(false);
		sql = "UPDATE produto SET descricao = ?, idCategoria = ?, preco = ? WHERE id = ?";
		this.statement = this.connection.prepareStatement(sql);
		
		this.statement.setString(1, prod.getNome());
		this.statement.setInt(2, prod.getIdCategoria());
		this.statement.setDouble(3, prod.getPreco());
		this.statement.setInt(4, prod.getId());
		
		this.estadoOperacao = this.statement.executeUpdate() > 0;
		this.connection.commit();
		this.statement.close();
		
		}catch(SQLException e) {
			this.connection.rollback();
			e.printStackTrace();
		}finally {
			System.out.println("Alterado");
			this.connection.close();
		}
		return this.estadoOperacao;
	}
	
	public boolean deletarProduto(int id_produto) throws SQLException {
		String sql = null;
		estadoOperacao = false;
		connection = obterConexao();

		try {
			connection.setAutoCommit(false);
			sql = "DELETE FROM produto WHERE id = ?";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id_produto);
			
			estadoOperacao = statement.executeUpdate() > 0 ;
			connection.commit();
			statement.close();
			
			
		}
		catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}finally{
			System.out.println("fechou");
			connection.close();
		}
		
		
		return estadoOperacao;
	}
}
