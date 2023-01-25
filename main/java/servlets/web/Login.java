package servlets.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//static Logger log = Logger.getLogger(Login.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("title", "Connexion");
		
		request.setAttribute("wrongCredential", request.getAttribute("wrongCredential"));
		
		UserDAO userDAO = UserDAO.getInstance();
		request.setAttribute("users", userDAO.getUsers());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		UserDAO userDAO = new UserDAO();
		if(request.getParameter("email") != "" && request.getParameter("password") != "") {
			User user = userDAO.isUserRegistered(request.getParameter("email"), request.getParameter("password"));
			System.out.println(user);
			if(user.getId() != 0) {
				request.getSession().setAttribute("login", user.getEmail());
				request.getSession().setAttribute("name", user.getName());
				request.getSession().setAttribute("id", user.getId());
				response.sendRedirect(request.getContextPath() + "/home");
			} else {
				request.setAttribute("wrongCredential", true);
				doGet(request, response);
			}
		}else {
			//log.debug("Hello this is a debug message");
			//log.info("Hello this is an info message");
			request.setAttribute("email", request.getParameter("email"));
			request.setAttribute("noCredentialGiven", true);
			doGet(request, response);
		}
	}

}
