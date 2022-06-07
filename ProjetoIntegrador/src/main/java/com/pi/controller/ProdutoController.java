package com.pi.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.pi.DAO.ProdutoDao;
import com.pi.model.Categoria;
import com.pi.model.Produto;

/**
 * Servlet implementation class ProdutoController
 */
@WebServlet(description="Controle do produto", urlPatterns="/produtos")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdutoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		if (opcao.equals("criarProduto")) {
			System.out.println("Pressionou a opcao de criar");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastroProduto.jsp");
			requestDispatcher.forward(request, response);
		}
		else if (opcao.equals("listarProdutos")) {
			ProdutoDao produtoDAO = new ProdutoDao();
			List<Produto> arrayProduto = new ArrayList<>();
			
			try {

				arrayProduto = produtoDAO.listarProd();

				request.setAttribute("arrayProduto", arrayProduto);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listarProdutos.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Pressionou a opcao de listar");
		}
		else if (opcao.equals("meditar")) {
			int id_produto = Integer.parseInt(request.getParameter("id_produto"));
			System.out.println("Editar id: " + id_produto);

			ProdutoDao produtoDAO = new ProdutoDao();
			Produto p = new Produto();

			try {
				p = produtoDAO.listarProduto(id_produto);
				request.setAttribute("produto", p);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/editarProduto.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (opcao.equals("deletar")) {
			ProdutoDao produtoDAO = new ProdutoDao();

			int id_produto = Integer.parseInt(request.getParameter("id_produto"));
			try {
				produtoDAO.deletarProduto(id_produto);
				System.out.println("Exclusao do id " + request.getParameter("id_usuario") + " realizado com sucesso!");

				HttpSession session = request.getSession();
				session.setAttribute("msgAviso", "Exclusao do produto realizada com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principalAdm.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcao = request.getParameter("opcao");
		
		if (opcao.equals("salvarProduto")) {
			ProdutoDao produtoDAO = new ProdutoDao();
			Produto produto = new Produto();
			boolean existe;
			
			produto.setNome(request.getParameter("nomeProduto"));
			produto.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
			produto.setPreco(Double.parseDouble(request.getParameter("preco")));
			
			
		
			try {
				existe = produtoDAO.inserirProduto(produto);
				
				if(existe) {
					System.out.println("Produto ja existe!");
					HttpSession session = request.getSession();
					session.setAttribute("msgAviso", "Produto já existe no cardápio! Insira outro nome!");
					session.setAttribute("msgAvisoCor", "red");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/cadastroProduto.jsp");
					requestDispatcher.forward(request, response);
				}
				else {
					System.out.println("Cadastro do produto realizado com sucesso!");
					HttpSession session = request.getSession();
					session.setAttribute("msgAviso", "Cadastro do produto realizado com sucesso!");
					session.setAttribute("msgAvisoCor", "green");
					
					if (session.getAttribute("usuario") == null || session.getAttribute("usuario") == "") {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
						requestDispatcher.forward(request, response);
					} else {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principalAdm.jsp");
						requestDispatcher.forward(request, response);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if (opcao.equals("editarProduto")) {
			ProdutoDao produtoDAO = new ProdutoDao();
			Produto p = new Produto();
			
	
			p.setId(Integer.parseInt(request.getParameter("id_produto")));
			p.setNome(request.getParameter("nomeProduto"));
			p.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
			p.setPreco(Double.parseDouble(request.getParameter("preco")));
	
			try {
				produtoDAO.alterarProduto(p);
				System.out.println(
						"Edicao do produto id " + request.getParameter("id_produto") + " realizado com sucesso!");
	
				HttpSession session = request.getSession();
				session.setAttribute("msgAviso", "Edicao realizada com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
	
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principalAdm.jsp");
				requestDispatcher.forward(request, response);
	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
