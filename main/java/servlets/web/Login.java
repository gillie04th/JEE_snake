package servlets.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(Login.class.getName());
       
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
		request.setAttribute("joueur", "toto");
		request.setAttribute("wrongCredential", request.getAttribute("wrongCredential"));
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		
		//System.out.println(request.getParameter("email") + " " + request.getParameter("password") + " " + request.getContextPath() + "/home");
		
		if(request.getParameter("email") != "" && request.getParameter("password") != "") {
			
			request.getSession().setAttribute("joueur", request.getParameter("email"));
			
			response.sendRedirect(request.getContextPath() + "/home");
		}else {
			log.debug("Hello this is a debug message");
			log.info("Hello this is an info message");
			request.setAttribute("wrongCredential", true);
			doGet(request, response);
		}
	}

}
