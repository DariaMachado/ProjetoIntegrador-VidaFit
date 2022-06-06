package com.pi.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pi.DAO.ProdutoDao;
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
	}

}
