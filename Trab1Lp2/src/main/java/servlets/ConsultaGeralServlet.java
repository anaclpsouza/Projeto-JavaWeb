package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Dono;
import classes.Veterinario;

@WebServlet("/ConsultaGeralServlet")
public class ConsultaGeralServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaGeralServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		ArrayList<Dono> donos = new ArrayList<>();
		ArrayList<Veterinario> vet = new ArrayList<>();
		Veterinario v = new Veterinario();
		Dono d = new Dono();
		String obj = request.getParameter("obj");
		if (obj.equals("vet")) {
			vet = v.getAll();
			for (Veterinario vets : vet) {
				if (vets.getTelefone() != null) { // Evita NullPointerException
					vets.setTelefone(vets.getTelefone().replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3"));
				}
			}
			request.setAttribute("vets", vet);
			rd = request.getRequestDispatcher("TodosVets.jsp");
		} else {
			donos = d.getAll();
			request.setAttribute("donos", donos);
			for (Dono dono : donos) {
				if (dono.getTelefone() != null) { 
					dono.setTelefone(dono.getTelefone().replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3"));
				}
			}
			for (Dono dono : donos) {
				if (dono.getCpf() != null) { 
					dono.setCpf(dono.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"));
				}
			}
			rd = request.getRequestDispatcher("TodosDonos.jsp");
		}

		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
