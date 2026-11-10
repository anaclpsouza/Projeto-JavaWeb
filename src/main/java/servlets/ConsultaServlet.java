package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Animal;
import classes.Dono;
import classes.Veterinario;

@WebServlet("/ConsultaServlet")
public class ConsultaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String obj = request.getParameter("objeto");
		int id = Integer.parseInt(request.getParameter("id"));
		Animal a = new Animal();
		Veterinario v = new Veterinario();
		Dono d = new Dono();
		RequestDispatcher rd = null;
		ArrayList<Animal> pets = new ArrayList<>();
		if (obj.equals("animal")) {
			a = a.getAnimal(id);
			v = v.getVet(a.getId_vet());
			d = d.getDono(a.getId_dono());
			request.setAttribute("pet", a);
			request.setAttribute("vet", v);
			request.setAttribute("dono", d);
			rd = request.getRequestDispatcher("ConsultaPet.jsp");
		} else if (obj.equals("vet")) {
			v = v.getVet(id);
			pets = v.getAnimaisVet(id);
			v.setTelefone(v.getTelefone().replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3"));
			request.setAttribute("vet", v);
			request.setAttribute("pets", pets);
			rd = request.getRequestDispatcher("ConsultaVet.jsp");
		} else if (obj.equals("dono")) {
			d = d.getDono(id);
			pets = d.getAnimaisDono(id);
			d.setTelefone(d.getTelefone().replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3"));
			d.setCpf(d.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"));
			request.setAttribute("dono", d);
			request.setAttribute("pets", pets);
			rd = request.getRequestDispatcher("ConsultaDono.jsp");
		}
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
