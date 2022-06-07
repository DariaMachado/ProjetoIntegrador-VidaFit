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


import com.pi.DAO.ClienteDao;
import com.pi.DAO.PedidoDao;
import com.pi.model.Cliente;
import com.pi.model.Pedido;

/**
 * Servlet implementation class ClienteController
 */
@WebServlet(description = "Controle das informacoes referentes aos clientes", urlPatterns = "/clientes")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcao = request.getParameter("opcao");
		
		if (opcao.equals("listarClientes")) {
			ClienteDao clienteDAO = new ClienteDao();
			List<Cliente> arrayCliente = new ArrayList<>();
			PedidoDao pedidoDAO = new PedidoDao();
			List<ArrayList<Pedido>> arrayPedidoCliente = new ArrayList<>();

			try {

				arrayCliente = clienteDAO.listarClientes();

				for (Cliente cl : arrayCliente) {
					arrayPedidoCliente.add((ArrayList<Pedido>) pedidoDAO
							.listarPedidoCliente(cl.getId()));
				}

				request.setAttribute("arrayCliente", arrayCliente);
				request.setAttribute("arrayPedidoCliente", arrayPedidoCliente);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/listarClientes.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Pressionou a opcao de listar");
		
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
