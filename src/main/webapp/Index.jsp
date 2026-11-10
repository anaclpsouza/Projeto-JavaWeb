<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="java.util.ArrayList, classes.Dono, classes.Veterinario, classes.Animal"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MiAuGuinho</title>
<link rel="icon" href="assets/img/logo-icon.ico" type="image/x-icon">
<link rel='stylesheet' href='assets/css/bootstrap/bootstrap.min.css' />
<link rel="stylesheet" href="assets/css/style.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<%
Veterinario vetLogado = (session != null) ? (Veterinario) session.getAttribute("vetLogado") : null;
String mensagem = (String) request.getAttribute("retorno");
%>
<body>
	<%
	if (vetLogado == null) {
	%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="IndexServlet"> <img
			src="assets/img/logo-png.png" alt="Logo" width="40" height="40"
			class="me-2"> <span id="site-name">MiAuGuinho</span>
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link" href="#">Veterinários</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Donos</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Animais</a></li>
			</ul>
		</div>
	</nav>

	<div class="container mt-4 tabela-index index">
		<h1 class="text-center">Realize Login para acessar as informações
			do sistema e realizar operações</h1>
		<p class="text-center">
			<a href="Login.jsp" class="btn btn-light"><i
				class="bi bi-person-circle"> Logar</i></a>
		</p>

	</div>

	<%
	} else {
	%>
	<nav class="navbar navbar-expand-lg">
		<a class="navbar-brand" href="IndexServlet"> <img
			src="assets/img/logo-png.png" alt="Logo" width="40" height="40"
			class="me-2"> <span id="site-name">MiAuGuinho</span>
		</a> <span class="navbar-text"><i class="bi bi-person-circle"
			id="icon-user"></i> Olá, ${vetLogado.nome}!</span>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ms-auto">
				<li class="nav-item"><a class="nav-link" href="FormVet.jsp"><i
						class="bi bi-heart-pulse" id="icon-nav"></i> Veterinários</a></li>
				<li class="nav-item"><a class="nav-link" href="FormDono.jsp"><i
						class="bi bi-house-heart" id="icon-nav"></i> Donos</a></li>
				<li class="nav-item"><a class="nav-link" href="PetServlet"><i
						class="bi bi-bug" id="icon-nav"></i> Animais</a>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet"><i
						class="bi bi-box-arrow-right" id="icon-nav"></i> Sair</a></li>
			</ul>
		</div>
	</nav>

		<div class="container mt-4 tabela-index">
			<h1 class="text-center">Informações dos animais</h1>
			<p class="text-center text-muted">Clique para visualizar / editar
				/ excluir informações sobre os animais. Clique no nome do
				Dono/Veterinário para visualizar suas informações.</p> <br>
			<div class="filter mt-2">
				<a type="submit" class="btn btn-secondary me-2" href="ConsultaGeralServlet?obj=vet">Ver
					Todos os Veterinários Cadastrados</a>

				<a type="submit" class="btn btn-primary me-2" href="ConsultaGeralServlet?obj=dono">Ver Todos
					os donos Cadastrados</a>
			</div>
	<c:if test="${not empty pets}">

			<div class="container mt-4">
				<div class="row text-center" id="cabecalho">
					<div class="col-lg-3 d-none d-md-block linha">Veterinário</div>
					<div class="col-lg-3 d-none d-md-block linha">Dono</div>
					<div class="col-lg-3 col-md-6 col-6 linha">Nome do Paciente</div>
					<div class="col-lg-3 col-md-6 col-6 linha">Operações</div>
				</div>
				<c:forEach items="${pets}" var="pet">
					<c:set var="donoNome" value="${''}" />
					<c:forEach items="${donos}" var="dono">
						<c:if test="${dono.id == pet.id_dono}">
							<c:set var="donoNome" value="${dono.nome}" />
						</c:if>
					</c:forEach>
					<c:set var="vetNome" value="${''}" />
					<c:set var="isResponsavel"
						value="${pet.id_vet == vetLogado.id ? true : false}" />
					<!-- Verifica se o veterinário logado é responsável -->
					<c:forEach items="${vets}" var="vet">
						<c:if test="${vet.id == pet.id_vet}">
							<c:set var="vetNome" value="${vet.nome}" />
						</c:if>
					</c:forEach>
					<div class="row">
						<div class="col-lg-3 d-none d-md-block linha">
							<a
								href="ConsultaServlet?objeto=vet&id=${pet.id_vet }&id_pet=${pet.id}">${vetNome}</a>
						</div>
						<div class="col-lg-3 d-none d-md-block linha">
							<a
								href="ConsultaServlet?objeto=dono&id=${pet.id_dono }&id_pet=${pet.id}">${donoNome}</a>
						</div>
						<div class="col-lg-3 col-md-6 col-6 linha">${pet.nome}</div>
						<div
							class="col-lg-3 col-md-6 col-6 d-flex align-items-center justify-content-center linha">
							<a href="ConsultaServlet?id=${pet.id }&objeto=animal"
								class="mx-1"><i class="bi bi-eye" id="olhoConsulta"></i></a>
							<c:if test="${isResponsavel}">
								<a href="AlteraServlet?id=${pet.id }&objeto=animal" class="mx-1"><i
									class="bi bi-pencil" id="lapisAlterar"></i></a>
								<a href="#deleteModal${pet.id}" data-bs-toggle="modal"
									class="mx-1"><i class="bi bi-trash" id="lixoDeletar"></i></a>
							</c:if>
						</div>
					</div>
					<!-- Modal de exclusão -->
					<div class="modal fade" id="deleteModal${pet.id}" tabindex="-1"
						aria-labelledby="deleteModalLabel${pet.id}" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="deleteModalLabel${pet.id}">Atenção!!</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<p>Você deseja realmente excluir o paciente ${pet.nome } ?</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Não, cancele.</button>
									<a href="ExcluirPetServlet?id=${pet.id}"><button
											class="btn btn-danger">Sim, delete.</button></a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
	</c:if>
		</div>



	<c:if test="${empty pets}">
		<div class="container mt-4 tabela-index">
			<p class="text-center text-muted">Ainda não há pacientes
				cadastrados.</p>
		</div>
	</c:if>
<%
	}
	%>
	<div class="footer mt-4">
		<p>© 2024 Clínica Veterinária Miauguinho</p>
	</div>

	<script>
 
        <%
if (mensagem != null) {%>
            alert("<%=mensagem%>");
	<%}%>
		
	</script>
	
	<script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script>

</body>

</html>
