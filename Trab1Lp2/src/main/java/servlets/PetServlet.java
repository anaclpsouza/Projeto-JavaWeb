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


@WebServlet("/PetServlet")
public class PetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public PetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Entrou no doGet");
		Dono d = new Dono();
		ArrayList<Dono> donos = d.getAll(); 
		Veterinario v = new Veterinario();
		ArrayList<Veterinario> vets = v.getAll();
		request.setAttribute("donos", donos);
		request.setAttribute("vets", vets);
		RequestDispatcher rd = request.getRequestDispatcher("FormPet.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String raca = request.getParameter("raca");
		int idade = Integer.parseInt(request.getParameter("idade"));
		double peso = Double.parseDouble(request.getParameter("peso"));
		int id_dono = Integer.parseInt(request.getParameter("id_dono"));
		int id_vet = Integer.parseInt(request.getParameter("id_vet"));
		String retorno;
		Animal a = new Animal(id_dono, id_vet, nome, raca, peso, idade);
		if (a.savePet()) {
			retorno = "Animal cadastrado com sucesso!";
		} else {
			retorno = "Erro ao cadastrar animal, tente novamente";
		}

		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		request.setAttribute("retorno", retorno);
		rd.forward(request, response);
	}

}
