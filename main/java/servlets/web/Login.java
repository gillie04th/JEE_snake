package servlets.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import validators.forms.LoginValidator;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log;
	private ArrayList<String> errors;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        log = Logger.getLogger(Login.class.getName());
		this.errors = new ArrayList<String>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("title", "Connexion");
		
		if(!this.errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.errors.clear();
		
		LoginValidator validator = new LoginValidator();
		validator.validateLogin(request);
		this.errors = validator.getResults();
		
		if(request.getSession().getAttribute("id") != null) {			
			log.info("Connexion de " + request.getSession().getAttribute("login"));
			response.sendRedirect(request.getContextPath() + "/home");
		}else {
			doGet(request, response);
		}
		
	}

}
