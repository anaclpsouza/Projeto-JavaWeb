package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Animal;


@WebServlet("/ExcluirPetServlet")
public class ExcluirPetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExcluirPetServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Animal a = new Animal();
		String retorno;
		if (a.deleteAnimal(id)) {
			retorno = "Animal deletado com sucesso!";
		} else {
			retorno = "Erro ao deletar animal, tente novamente";
		}
		request.setAttribute("retorno", retorno);
		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
