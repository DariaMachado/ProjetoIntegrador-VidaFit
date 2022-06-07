<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

	<c:if test="${empty sessionScope['usuario']}">
		<c:set var="msgAviso" value="Você precisa entrar no sistema para ter acesso a esse conteúdo" scope="session" />
		<c:redirect url="index.jsp" />
	</c:if>
	
	<meta charset="UTF-8">
	<title>Listar Clientes</title>
</head>
<body>
<div class="container">
	<table class="orange lighten-4">
		<tr>
			<td>
				<font size="3">
					Bem vindo <c:out value="${sessionScope.usuario.primeiroNome}"/>, deseja fazer <a href="logout">Logout?</a>
				</font>
			</td>
		</tr>
	</table>
	
	<h1>Clientes</h1>
	
	<table border="1" class="striped" >
		<tr>
			<td>ID, ação:</td>
			<td>Nome: </td>
			<td>CPF: </td>
			<td>Telefone: </td>
			<td>Nº Pedido: </td>
			<td>Data do Pedido: </td>
			<td>Status:</td>
		</tr>
		
		<c:forEach var="arrayCliente" items="${arrayCliente}" varStatus="loopCliente">
			<tr>
				<td> <a class="waves-effect waves-light btn" href="clientes?opcao=meditar&id_usuario=<c:out value="${arrayCliente.id}"></c:out>"> <c:out value="${arrayCliente.id}"></c:out>, Alterar Status </a> </td>
				<td><c:out value="${arrayCliente.nomeCompleto}"></c:out></td>
				<td><c:out value="${arrayCliente.cpf}"></c:out></td>
				<td><c:out value="${arrayCliente.telefone}"></c:out></td>
				<td><c:forEach var="arrayPedidoCliente" items="${arrayPedidoCliente[loopCliente.index]}">
					<c:out value="${arrayPedidoCliente.id}"></c:out><br>
				</c:forEach></td>
				<td><c:forEach var="arrayPedidoCliente" items="${arrayPedidoCliente[loopCliente.index]}">
					<c:out value="${arrayPedidoCliente.data}"></c:out><br>
				</c:forEach></td>
				<td><c:forEach var="arrayPedidoCliente" items="${arrayPedidoCliente[loopCliente.index]}">
					<c:out value="${arrayPedidoCliente.idStatus}"></c:out><br>
				</c:forEach></td>
			</tr>
		</c:forEach>
		<tr>
			<td class="left-align">
				<a class="waves-effect waves-light btn" href= "usuario?opcao=voltar&view=principalAdm.jsp"><i class="material-icons right">arrow_back</i>Voltar</a>
			</td>
		</tr>
		
	</table>
</div>
</body>
</html>