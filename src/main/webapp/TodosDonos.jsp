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
		<div class="row">
			<div class="col-12 mb-3">
				<h1 class="text-center">Donos Cadastrados</h1>
				<hr />
			</div>
		</div>

		<c:choose>
			<c:when test="${not empty donos}">
				<div class="row">
					<c:forEach var="dono" items="${donos}">
						<div class="col-md-6 col-lg-4 mb-4">
							<div class="card h-100 shadow-sm">
								<div class="card-body">
									<h5 class="card-title">${dono.nome}</h5>
									<p class="card-text">
										<strong>Idade:</strong> ${dono.idade} anos
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
									<div class="d-grid gap-2 d-md-block">
										<a href="ConsultaServlet?id=${dono.id}&objeto=dono"
											class="btn btn-outline-secondary btn-sm"> <i
											class="bi bi-info-circle"></i> Ver Detalhes
										</a> <a href="AlteraServlet?id=${dono.id}&objeto=dono"
											class="btn btn-outline-primary btn-sm"> <i
											class="bi bi-pencil"></i> Editar
										</a>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-12 text-center">
					<p class="alert alert-warning">Nenhum dono cadastrado no
						momento.</p>
					<a href="FormDono.jsp" class="btn btn-success"> <i
						class="bi bi-plus-circle"></i> Cadastrar Dono
					</a>
				</div>
			</c:otherwise>
		</c:choose>
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