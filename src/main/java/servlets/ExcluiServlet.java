package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Dono;
import classes.Veterinario;

@WebServlet("/ExcluiServlet")
public class ExcluiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExcluiServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String obj = request.getParameter("objeto");
		int id = Integer.parseInt(request.getParameter("id"));
		Dono d = new Dono();
		Veterinario v = new Veterinario();
		String retorno = null;
		if (obj.equals("dono")) {
			if (d.deleteDono(id)) {
				retorno = "Dono excluído com sucesso!";
			} else {
				retorno = "Erro ao cadastrar animal, tente novamente";
			}
		} else if (obj.equals("vet")) {
			if (v.deleteVet(id)) {
				retorno = "Veterinário excluído com sucesso!";
			} else {
				retorno = "Erro ao cadastrar animal, tente novamente";
			}
		} 
		request.setAttribute("retorno", retorno);
		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
