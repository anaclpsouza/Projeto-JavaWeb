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

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Dono d = new Dono();
		ArrayList<Dono> donos = d.getAll(); 
		Veterinario v = new Veterinario();
		ArrayList<Veterinario> vets = v.getAll();
		Animal a = new Animal();
		ArrayList<Animal> pets = a.getAll();
		request.setAttribute("pets", pets);
		request.setAttribute("donos", donos);
		request.setAttribute("vets", vets);
		RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
