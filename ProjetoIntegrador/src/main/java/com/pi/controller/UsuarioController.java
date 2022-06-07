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

import com.pi.DAO.EnderecoClienteDao;
import com.pi.DAO.UsuarioDao;
import com.pi.model.Cliente;
import com.pi.model.Endereco;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet(description = "Controle das informacoes referentes aos usuarios", urlPatterns = "/usuario")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcao = request.getParameter("opcao");

		if (opcao.equals("criar")) {
			System.out.println("Pressionou a opcao de criar");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/criar.jsp");
			requestDispatcher.forward(request, response);

		} 	
		else if (opcao.equals("voltar")) {
			String url = "";
			HttpSession session = request.getSession();
			if (session.getAttribute("usuario") == null || session.getAttribute("usuario") == "") {
				url = "index.jsp";
			} else {
				String view = request.getParameter("view");

				url = "views/" + view;
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcao = request.getParameter("opcao");

		if (opcao.equals("guardar")) {
			UsuarioDao usuarioDAO = new UsuarioDao();
			EnderecoClienteDao enderecoClienteDao = new EnderecoClienteDao();
			Cliente usuario = new Cliente();
			
			
			usuario.setPrimeiroNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setCpf(request.getParameter("cpf"));
			usuario.setRg(request.getParameter("rg"));
			usuario.setTelefone(request.getParameter("tel"));
			usuario.setSenha(Integer.parseInt(request.getParameter("senha")));
			//Como somente clientes realizarão o cadastro, setamos o tipo de usuário diretamente;
			usuario.setIdTipo(2);
			String sobrenome = request.getParameter("sobrenome");
			String nome = usuario.getPrimeiroNome();
			usuario.setNomeCompleto(nome + " " + sobrenome);
			

			try {
				int id_usuario = (int) usuarioDAO.inserirUsuario(usuario);

				if (request.getParameter("rua") != "") {
					Endereco endereco =  new Endereco();

					endereco.setRua(request.getParameter("rua"));
					endereco.setNumero(Integer.parseInt(request.getParameter("num01")));
					endereco.setComplemento(request.getParameter("complemento"));
					endereco.setCep(request.getParameter("cep"));
					endereco.setCidade(request.getParameter("cidade"));
					endereco.setEstado(request.getParameter("estado"));
					endereco.setIdCliente(id_usuario);
					
					enderecoClienteDao.inserirEnderecoCliente(endereco);
				}

				if (request.getParameter("rua2") != "") {
					Endereco endereco =  new Endereco();

					endereco.setRua(request.getParameter("rua2"));
					endereco.setNumero(Integer.parseInt(request.getParameter("num02")));
					endereco.setComplemento(request.getParameter("complemento2"));
					endereco.setCep(request.getParameter("cep2"));
					endereco.setCidade(request.getParameter("cidade2"));
					endereco.setEstado(request.getParameter("estado2"));
					endereco.setIdCliente(id_usuario);
					
					enderecoClienteDao.inserirEnderecoCliente(endereco);
				}

				System.out.println("Cadastro realizado com sucesso!");
				HttpSession session = request.getSession();
				session.setAttribute("msgAviso", "Cadastro realizado com sucesso!");
				session.setAttribute("msgAvisoCor", "green");
				if (session.getAttribute("usuario") == null || session.getAttribute("usuario") == "") {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
					requestDispatcher.forward(request, response);
				} else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
