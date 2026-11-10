package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Dono;

@WebServlet("/DonoServlet")
public class DonoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DonoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		int idade = Integer.parseInt(request.getParameter("idade"));
		String telefone = request.getParameter("telefone");
		String cpf = request.getParameter("cpf");
		String endereco = request.getParameter("endereco");
		if (telefone != null) {
			telefone = telefone.replaceAll("[^0-9]", "");
		}
		if (cpf != null) {
			cpf = cpf.replaceAll("[^0-9]", "");
		}
		String retorno;
		Dono d = new Dono(nome, idade, telefone, cpf, endereco);
		if (d.saveDono()) {
			retorno = "Dono cadastrado com sucesso!";
		} else {
			retorno = "Erro ao cadastrar dono, tente novamente";
		}

		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		request.setAttribute("logado", true);
		request.setAttribute("retorno", retorno);
		rd.forward(request, response);
	}

}
