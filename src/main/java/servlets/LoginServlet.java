package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Dono;
import classes.Veterinario;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		boolean logado = false;
		RequestDispatcher rd = null;
		String user = request.getParameter("user");
		String senha = request.getParameter("senha");
		Veterinario vets = new Veterinario();
		ArrayList<Veterinario> listaVets = vets.getAll();
		for (Veterinario v : listaVets) {
			if (v.getUser().equals(user) && v.getSenha().equals(senha)) {
				session.setAttribute("logado", true);
			    session.setAttribute("vetLogado", v);
			    logado = true;
				break;
			}
		}
		if(logado) {
			rd = request.getRequestDispatcher("IndexServlet");
		} else {
			request.setAttribute("retorno", "Erro nas credenciais, tente novamente.");
			rd = request.getRequestDispatcher("Login.jsp");
		}
		
		rd.forward(request, response);
	}

}
