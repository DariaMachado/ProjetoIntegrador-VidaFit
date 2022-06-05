<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script> 
	<script src="js/validaFormCadastro.js" type="text/javascript"></script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		
	<meta charset="UTF-8">
	<title>Adicionar um Usuário</title>
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
				Bem vindo <c:out value="${sessionScope.usuario.nome}"/>, deseja fazer <a href="logout">Logout?</a>
			</font>
		</td>
	</tr>
</table>
</c:if>
	<h1>Adicionar um Usuário</h1>
	<form id="form_usuario" action="usuario" method="post">
		<input type="hidden" name="opcao" value="guardar">
		<table border="1" class="highlight">
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">account_box</i>
						<input id="nome_prefix" type="text" class="validate" campo-obrigatorio size="200" name="nome">
						<label for="nome_prefix">Primeiro Nome <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">account_box</i>
						<input id="nome_prefix" type="text" class="validate" campo-obrigatorio size="200" name="sobrenome">
						<label for="nome_prefix">Sobrenome <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">email</i>
						<input id="email_prefix" type="text" class="validate" campo-obrigatorio size="300" name="email">
						<label for="email_prefix">E-mail <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">subtitles</i>
						<input id="cpf_prefix" type="text" class="validate" campo-obrigatorio maxlength="11" size="11" name="cpf">
						<label for="cpf_prefix">CPF <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">featured_video</i>
						<input id="rg_prefix" type="text" class="validate" campo-obrigatorio maxlength="18" size="18" name="rg">
						<label for="rg_prefix">RG <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">phone</i>
						<input id="tel_prefix" type="text" class="validate" campo-obrigatorio size="15" name="tel">
						<label for="tel_prefix">Telefone <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-field col s6">
						<i class="material-icons prefix">security</i>
						<input id="senha_prefix" type="password" class="validate" name="senha">
						<label for="senha_prefix">Senha <font color="red">(Obrigatório)</font></label>
			        </div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="row">
						<div class="input-field col s12">
							<div class="left-align">
								<i class="material-icons prefix">person_pin_circle</i>
								<label>Endereço Principal <font color="red">(Obrigatório)</font></label>
							</div>
							<div class="input-field col s9">
								<input id="rua_prefix" type="text" name="rua" maxlength="200" size="5" class="validate" campo-obrigatorio>
								<label for="rua_prefix">Rua </label>
					        </div>
					        
							<div class="input-field col s3">
								<input id="num01_prefix" type="number" name="num01" maxlength="9" size="13" class="validate" campo-obrigatorio>
								<label for="num01_prefix">Número </label>
					        </div>
					        <div class="input-field col s6">
								<input id="complemento_prefix" type="text" name="complemento" maxlength="300" size="13" class="validate" campo-obrigatorio>
								<label for="complemento_prefix">Complemento </label>
					        </div>
					        <div class="input-field col s6">
								<input id="cep_prefix" type="text" name="cep" maxlength="8" size="8" class="validate" campo-obrigatorio>
								<label for="cep_prefix">CEP </label>
					        </div>
					        <div class="input-field col s8">
								<input id="cidade_prefix" type="text" name="cidade" maxlength="200" size="13" class="validate" campo-obrigatorio>
								<label for="cidade_prefix">Cidade </label>
					        </div>
					        <div class="input-field col s4">
								<input id="estado_prefix" type="text" name="estado" maxlength="2" size="2" class="validate" campo-obrigatorio>
								<label for="estado_prefix">Estado </label>
					        </div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="row">
						<div class="input-field col s12">
							<div class="left-align">
								<i class="material-icons prefix">person_pin_circle</i>
								<label>Endereço Secundário</label>
							</div>
							<div class="input-field col s9">
								<input id="rua2_prefix" type="text" name="rua2" maxlength="200" size="5">
								<label for="rua2_prefix">Rua </label>
					        </div>
					        
							<div class="input-field col s3">
								<input id="num02_prefix" type="number" name="num02" maxlength="9" size="13">
								<label for="num02_prefix">Número </label>
					        </div>
					        <div class="input-field col s6">
								<input id="complemento2_prefix" type="text" name="complemento2" maxlength="300" size="13">
								<label for="complemento2_prefix">Complemento </label>
					        </div>
					        <div class="input-field col s6">
								<input id="cep_prefix" type="text" name="cep2" maxlength="9" size="13">
								<label for="cep_prefix">CEP </label>
					        </div>
					        <div class="input-field col s8">
								<input id="cidade2_prefix" type="text" name="cidade2" maxlength="200" size="13">
								<label for="cidade2_prefix">Cidade </label>
					        </div>
					        <div class="input-field col s4">
								<input id="estado2_prefix" type="text" name="estado2" maxlength="2" size="2">
								<label for="estado2_prefix">Estado </label>
					        </div>
						</div>
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
		  <button class="btn-large waves-effect waves-light" type="submit" value="Guardar" onclick="">Guardar
		    <i class="material-icons right">add_circle_outline</i>
		  </button>
		</div>
		<br>
		<c:if test="${!empty sessionScope['usuario']}">
			<div class="center-align">
				<a class="waves-effect waves-light btn-large" href= "usuario?opcao=voltar&view=principal.jsp"><i class="material-icons right">arrow_back</i>Voltar</a>
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