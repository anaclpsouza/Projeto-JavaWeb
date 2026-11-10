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
Animal pet = (Animal) request.getAttribute("pet");
ArrayList<Animal> pets = (ArrayList<Animal>) request.getAttribute("pets");
%>
<body>
	<%
	if (vetLogado == null) {
	%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="IndexServlet?logadoF=${logadoF}"> <img
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
				<li class="nav-item"><a class="nav-link" href="FormVet.jsp">Veterinários</a></li>
				<li class="nav-item"><a class="nav-link" href="FormDono.jsp">Donos</a></li>
				<li class="nav-item"><a class="nav-link" href="PetServlet">Animais</a></li>
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
	<div class="container mt-4">
		<div class="row mb-4">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title" style="font-size: 1.5rem;">Informações
							do Dono</h5>
						<p class="card-text">
							<strong>Nome:</strong> ${dono.nome}
						</p>
						<p class="card-text">
							<strong>Idade:</strong> ${dono.idade}
						</p>
						<p class="card-text">
							<strong>Telefone:</strong> ${dono.telefone}
						</p>
						<p class="card-text">
							<strong>Cpf:</strong> ${dono.cpf}
						</p>
						<p class="card-text">
							<strong>Endereço:</strong> ${dono.endereco}
						</p>
						<a href="AlteraServlet?id=${dono.id }&objeto=dono"
							class="btn btn-outline-dark">Editar Dados</a> <a
							href="#ExcluiModal" class="btn btn-outline-danger"
							data-bs-toggle="modal" data-bs-target="#ExcluiModal">Excluir
							Dono</a> <a href="IndexServlet" class="btn btn-outline-secondary">Voltar</a>
					</div>
				</div>
			</div>
		</div>


		<div class="row">
			<h2 class="mb-3">Animais do Dono</h2>
			<c:forEach var="animal" items="${pets}">
				<div class="col-12 mb-3">
					<div class="card">
						<div class="row g-0">
							<div class="col-md-8">
								<div class="card-body">
									<h5 class="card-title">${animal.nome}</h5>
									<p class="card-text">Raça: ${animal.raca}</p>
									<p class="card-text">Peso: ${animal.peso}</p>
									<p class="card-text">Idade: ${animal.idade}</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<!-- Modal de Exclusão -->
	<div class="modal fade" id="ExcluiModal" tabindex="-1"
		aria-labelledby="confirmModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="confirmModalLabel">Confirmação de
						Exclusão</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<p>Você tem certeza que deseja excluir este dono? Essa ação
						apagará todos os animais associados. Esta ação não pode ser
						desfeita.</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<a href="ExcluiServlet?id=${dono.id }&objeto=dono"
						class="btn btn-danger">Excluir</a>
				</div>
			</div>
		</div>
	</div>


	<%
	}
	%>

	<div class="footer mt-4">
		<p>© 2024 Clínica Veterinária Miauguinho</p>
	</div>
	<script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script>

</body>

</html>
