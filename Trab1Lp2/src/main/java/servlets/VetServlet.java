package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Veterinario;

@WebServlet("/VetServlet")
public class VetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VetServlet() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		int idade = Integer.parseInt(request.getParameter("idade"));
		String telefone = request.getParameter("telefone");
		String usuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		if (telefone != null) {
			telefone = telefone.replaceAll("[^0-9]", "");
		}

		String retorno;
		Veterinario v = new Veterinario(nome, telefone, idade, usuario, senha);
		if (v.saveVet()) {
			retorno = "Veterinário cadastrado com sucesso!";
		} else {
			retorno = "Erro ao cadastrar veterinário, tente novamente";
		}

		request.setAttribute("retorno", retorno);
		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		rd.forward(request, response);

	}

}
