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

import com.pi.DAO.UsuarioDao;
import com.pi.model.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description="Controle do login", urlPatterns="/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcao = request.getParameter("opcao");
		if(opcao.equals("login")) {
			UsuarioDao usuarioDao = new UsuarioDao();
			Usuario usuario = new Usuario();
			try {
				usuario.setEmail(request.getParameter("email"));
				usuario.setSenha(Integer.parseInt(request.getParameter("senha")));
				usuario = usuarioDao.login(usuario);
				HttpSession session = request.getSession();
				
				if(usuario.getEmail() != null) {
					//conseguiu logar
					session.setAttribute("usuario", usuario);
					session.setAttribute("msgAviso", "Login realizado com sucesso!");
					session.setAttribute("msgAvisoCor", "green");
					if(usuario.getIdTipo() == 1) {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principalAdm.jsp");
						requestDispatcher.forward(request, response);
					}else if(usuario.getIdTipo() == 2) {
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/principal.jsp");
						requestDispatcher.forward(request, response);
					}
					
				}
				else {
					//nao logou
					session.setAttribute("msgAviso", "E-mail e/ou senha invalido!");
					session.setAttribute("msgAvisoCor", "red");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
					requestDispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
