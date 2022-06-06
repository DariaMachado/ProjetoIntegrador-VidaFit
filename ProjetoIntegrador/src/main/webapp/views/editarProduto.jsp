<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script> 
	<script src="js/validaFormCadastroProduto.js" type="text/javascript"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
	
	
	<c:if test="${empty sessionScope['usuario']}">
		<c:set var="msgAviso" value="Você precisa entrar no sistema para ter acesso a esse conteúdo" scope="session" />
		<c:redirect url="index.jsp" />
	</c:if>
		
	<meta charset="UTF-8">
	<title>Adicionar um Pedido</title>
	<script type="text/javascript">
		  $(document).ready(function(){
		    $('select').formSelect();
		  });
	</script>

</head>
<body>
<div class="container">

<c:if test="${!empty sessionScope['usuario']}">
<table class="orange lighten-4">
	<tr>
 		<td>
			<font size="3">
				Bem vindo <c:out value="${sessionScope.usuario.primeiroNome}"/>, deseja fazer <a href="logout">Logout?</a>
			</font>
		</td>
	</tr>
</table>
	</c:if>
	<h1>Edição de Produto</h1>
	<form id="form_produto" action="produtos" method="post">
		<input type="hidden" name="opcao" value="editarProduto">
		<input type="hidden" name="id_produto" value="${produto.id}">
		<table border="1" class="highlight">
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">local_grocery_store</i> <input
							id="prod_prefix" placeholder="Digite o nome..." type="text"
							class="validate" campo-obrigatorio size="300" name="nomeProduto" value="${produto.nome}">
						<label for="prod_prefix">Nome do Produto <font color="red">(Obrigatório)</font></label>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">monetization_on</i> <input
							id="preco_prefix" placeholder="Digite o valor..." type="text"
							class="validate" campo-obrigatorio name="preco" value="${produto.preco}"> <label for="preco_prefix">Preço
							<font color="red">(Obrigatório)</font>
						</label>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s12">
					<i class="material-icons prefix">assignment</i>
						<select name="categoria" id="${produto.idCategoria}" class="validate" campo-obrigatorio >
							<option  value="1">Low Carb</option>
							<option  value="2">Sobremesa</option>
							<option  value="3">Bebidas</option>
						</select>  <label>Escolha a categoria: <font color="red">(Obrigatório)</font></label> 
					</div>
				</td>
			</tr>
		</table>
		<table>
			<tr>
				<td class="center-align">
					<font size="5">
						Tudo que estiver em
						<font color="red">
							 vermelho 
						</font>
					é obrigatório.
					</font>
				</td>
			</tr>
		<c:if test="${!empty sessionScope.msgAviso }">
			<tr>
				<td class="center-align">
					<font size="5" color="<c:out value="${sessionScope.msgAvisoCor}"/>">
						<c:out value="${sessionScope.msgAviso}"/>
					</font>
				</td>
			</tr>
		</c:if>
		</table>
		<div class="center-align">
		  <button class="btn-large waves-effect waves-light" type="submit" value="salvar" onclick="">Salvar edição
		    <i class="material-icons right">playlist_add_check</i>
		  </button>
		</div>
		<br>
		<c:if test="${!empty sessionScope['usuario']}">
			<div class="center-align">
				<a class="waves-effect waves-light btn-large" href= "usuario?opcao=voltar&view=principalAdm.jsp"><i class="material-icons right">arrow_back</i>Voltar</a>
			</div>
		</c:if>
		<c:if test="${empty sessionScope['usuario']}">
			<div class="center-align">
				<a class="waves-effect waves-light btn-large" href= "usuario?opcao=voltar"><i class="material-icons right">arrow_back</i>Voltar</a>
			</div>
		</c:if>
		<br>
	</form>
</div>
<c:set var="msgAviso" value="" scope="session" />
</body>
</html>