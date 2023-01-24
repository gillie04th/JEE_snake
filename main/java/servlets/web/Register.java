package servlets.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("title", "Inscription");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<String> errors = new ArrayList<String>();
		
		
		var pseudo = request.getParameter("pseudo");
		
		if(pseudo.isEmpty()) {
			errors.add("Veuillez renseigner votre pseudo!");
		}
		/*else if(
		 */
		else {
			request.setAttribute("pseudo", pseudo);
		}
		
		
		var email = request.getParameter("email");
		var emailConfirm = request.getParameter("emailConfirm");
		
		if(email.isEmpty()) {
			errors.add("Veuillez renseigner votre email!");
		}
		else if(emailConfirm.isEmpty()) {
			errors.add("Veuillez confirmer votre email!");
		}
		else if(!email.equals(emailConfirm)) {
			errors.add("Votre email n'est pas confirmé!");
		}	
		else {
			request.setAttribute("email", email);
			request.setAttribute("emailConfirm", emailConfirm);
		}
		
		
		var password = request.getParameter("password");
		var passwordConfirm = request.getParameter("passwordConfirm");
				
		if(password.isEmpty()) {
			errors.add("Veuillez renseigner votre mot de passe!");
		}
		else if(password.length()<8) {
			errors.add("Votre mot de passe doit doit être au moins de 8 charactères!");
		}
		else if(passwordConfirm.isEmpty()) {
			errors.add("Veuillez confirmer votre mot de passe!");
		}
		else if(!password.equals(passwordConfirm)) {
			errors.add("Votre mot de passe n'est pas confirmé!");
		}	
		
		
		request.setAttribute("errors", errors);
		
		doGet(request, response);
		
	}

}
