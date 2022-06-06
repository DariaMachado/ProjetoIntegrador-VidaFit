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
	<title>Listar Produtos</title>
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
	
	<h1>Lista de Produtos - Cardápio</h1>
	
	<table border="1" class="striped">
		<tr>
			<td>ID, ação:</td>
			<td>Nome: </td>
			<td>Preço: </td>
			<td>Categoria: </td>
			<td>Ação: </td>
		</tr>
		
		<c:forEach var="arrayProduto" items="${arrayProduto}" varStatus="loopProduto">
			<tr>
				<td> <a class="waves-effect waves-light btn" href="produtos?opcao=meditar&id_produto=<c:out value="${arrayProduto.id}"></c:out>"> <c:out value="${arrayProduto.id}"></c:out>, Editar </a> </td>
				<td><c:out value="${arrayProduto.nome}"></c:out></td>
				<td><c:out value="${arrayProduto.preco}"></c:out></td>
				<td><c:out value="${arrayProduto.idCategoria}"></c:out></td>
				<td> <a class="waves-effect waves-light btn" href="produtos?opcao=deletar&id_produto=<c:out value="${arrayProduto.id}"></c:out>"> Deletar </a> </td>
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