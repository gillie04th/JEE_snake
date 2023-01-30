package servlets.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import DAO.DAOFactory;
import DAO.UserDAO;
import models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log;
	private UserDAO userDAO;
	private ArrayList<String> errors;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        log = Logger.getLogger(Login.class.getName());
		DAOFactory factory = DAOFactory.getInstance();
		this.userDAO = (UserDAO) factory.getUserDAO();
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
			
		if(request.getParameter("email") != "" && request.getParameter("password") != "") {
			User user = userDAO.isUserRegistered(request.getParameter("email"), request.getParameter("password"));
			if(user != null) {
				request.getSession().setAttribute("login", user.getEmail());
				request.getSession().setAttribute("name", user.getName());
				request.getSession().setAttribute("id", user.getId());
				response.sendRedirect(request.getContextPath() + "/home");
			} else {
				request.setAttribute("wrongCredential", true);
				doGet(request, response);
			}
		} else {
			log.debug("Hello this is a debug message");
			log.error("Hello this is an info message");
			request.setAttribute("email", request.getParameter("email"));
			this.errors.add("Il manque une partie des informations de connexion");
			doGet(request, response);
		}
		
		if(!this.errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}
	}

}
