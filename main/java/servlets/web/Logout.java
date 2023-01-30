package servlets.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AuthorizationException;
import services.AuthorizationService;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArrayList<String> errors;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        this.errors = new ArrayList<String>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AuthorizationService.isUserLogged(request);
			request.getSession().invalidate();
		}catch(AuthorizationException e) {
			this.errors.add("Vous n'êtes pas connecté ... Ca sert à rien de forcer !");
			request.setAttribute("errors", errors);
		}
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
