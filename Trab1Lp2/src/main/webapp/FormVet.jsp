<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.ArrayList, classes.Dono, classes.Veterinario, classes.Animal"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="assets/img/logo-icon.ico" type="image/x-icon">
<link rel='stylesheet' href='assets/css/bootstrap/bootstrap.min.css' />
<link rel="stylesheet" href="assets/css/style.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.7/jquery.inputmask.min.js"></script>

<title>Cadastro de Veterinários</title>
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
	<div class="form-cadastro">
		<div class="login-card mx-auto">
			<h1 class="text-center">Cadastro de Veterinários</h1>
			<form method="post" action="VetServlet">
				<div class="mb-3">
					<label for="nome" class="form-label">Nome</label> <input
						type="text" class="form-control" id="nome" name="nome" required>
				</div>
				<div class="mb-3">
					<label for="idade" class="form-label">Idade</label> <input
						type="number" class="form-control" id="idade" name="idade"
						required>
				</div>
				<div class="mb-3">
					<label for="telefone" class="form-label">Telefone</label> <input
						type="tel" class="form-control" id="telefone" name="telefone"
						required>
				</div>
				<div class="mb-3">
					<label for="usuario" class="form-label">Usuário</label> <input
						type="text" class="form-control" id="usuario" name="usuario"
						required>
				</div>
				<div class="mb-3">
					<label for="senha" class="form-label">Senha</label> <input
						type="password" class="form-control" id="senha" name="senha"
						required>
				</div>
				<button type="submit" class="btn btn-primary w-100">Cadastrar</button>
			</form>
		</div>
	</div>
	<%
	} 
%>
	<div class="footer mt-4">
		<p>© 2024 Clínica Veterinária Miauguinho</p>
	</div>
	<script>
    $(document).ready(function() {
        $("#telefone").inputmask("(99) 99999-9999");
    });
</script>
	<script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script>

</body>
</html>