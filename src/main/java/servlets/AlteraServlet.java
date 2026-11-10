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

@WebServlet("/AlteraServlet")
public class AlteraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AlteraServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String obj = request.getParameter("objeto");
		RequestDispatcher rd = null;
		Dono d = new Dono();
		Veterinario v = new Veterinario();
		Animal a = new Animal();

		if (obj.equals("animal")) {
			ArrayList<Dono> donos = d.getAll();
			ArrayList<Veterinario> vets = v.getAll();
			a = a.getAnimal(id);
			request.setAttribute("donos", donos);
			request.setAttribute("vets", vets);
			request.setAttribute("animal", a);
			rd = request.getRequestDispatcher("AlteraPet.jsp");
		} else if (obj.equals("vet")) {
			v = v.getVet(id);
			request.setAttribute("vet", v);
			rd = request.getRequestDispatcher("AlteraVet.jsp");
		} else if (obj.equals("dono")) {
			d = d.getDono(id);
			request.setAttribute("dono", d);
			rd = request.getRequestDispatcher("AlteraDono.jsp");
		}

		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String obj = request.getParameter("objeto");

		if (obj.equals("animal")) {
			String nome = request.getParameter("nome");
			String raca = request.getParameter("raca");
			int idade = Integer.parseInt(request.getParameter("idade"));
			double peso = Double.parseDouble(request.getParameter("peso"));
			int id_dono = Integer.parseInt(request.getParameter("id_dono"));
			int id_vet = Integer.parseInt(request.getParameter("id_vet"));
			String retorno;
			Animal a = new Animal(id_dono, id_vet, nome, raca, peso, idade);
			if (a.updatePet(id)) {
				retorno = "Animal alterado com sucesso!";
			} else {
				retorno = "Erro ao cadastrar animal, tente novamente";
			}
			request.setAttribute("retorno", retorno);
		} else if (obj.equals("vet")) {
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			String telefone = request.getParameter("telefone");
			String user = request.getParameter("usuario");
			String senha = request.getParameter("senha");
			String retorno;
			Veterinario v = new Veterinario(nome, telefone, idade, user, senha);
			if (v.updateVet(id)) {
				retorno = "Veterinário alterado com sucesso!";
			} else {
				retorno = "Erro ao cadastrar animal, tente novamente";
			}
			request.setAttribute("retorno", retorno);
		} else if (obj.equals("dono")) {
			String nome = request.getParameter("nome");
			int idade = Integer.parseInt(request.getParameter("idade"));
			String telefone = request.getParameter("telefone");
			String cpf = request.getParameter("cpf");
			String endereco = request.getParameter("endereco");
			String retorno;
			Dono d = new Dono(nome, idade, telefone, cpf, endereco);
			if (d.updateDono(id)) {
				retorno = "Dono alterado com sucesso!";
			} else {
				retorno = "Erro ao cadastrar dono, tente novamente";
			}
			request.setAttribute("retorno", retorno);
		}
		RequestDispatcher rd = request.getRequestDispatcher("IndexServlet");
		rd.forward(request, response);
	}

}
