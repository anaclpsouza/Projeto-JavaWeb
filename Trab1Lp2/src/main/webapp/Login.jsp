<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MiAuGuinho</title>
<link rel="icon" href="assets/img/logo-icon.ico" type="image/x-icon">
<link rel='stylesheet' href='assets/css/bootstrap/bootstrap.min.css' />
<link rel="stylesheet" href="assets/css/style.css" />
<link rel="icon" href="/img/logo-icon.ico" type="image/x-icon">
</head>
<body class="login">
	<div class="login-card text-center">
		<img src="assets/img/logo-png.png" alt="Miauguinho Logo"
			class="mb-4 rounded-circle" id="img-logo">
		<h1>Clínica Veterinária MiAuGuinho</h1>
		<p class="mb-4">Área de acesso exclusivo para funcionários</p>

		<form action="LoginServlet" method="POST">
			<div class="mb-3 text-start">
				<label for="user" class="form-label">Usuário</label> <input
					type="text" id="user" name="user" class="form-control"
					placeholder="@" required>
			</div>
			<div class="mb-3 text-start">
				<label for="password" class="form-label">Senha</label> <input
					type="password" id="senha" name="senha" class="form-control"
					placeholder="Digite sua senha" required>
			</div>
			<button type="submit" class="btn btn-primary w-100">Entrar</button>
		</form>

	</div>
	<div class="footer mt-4">
		<p>© 2024 Clínica Veterinária Miauguinho</p>
	</div>
	<script>
 
        <%String mensagem = (String) request.getAttribute("retorno");
if (mensagem != null) {%>
            alert("<%=mensagem%>");
	<%}%>
		
	</script>
	<script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>