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
 * Servlet implementation class Ranking
 */
@WebServlet("/ranking")
public class Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> errors;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ranking() {
        super();
        this.errors = new ArrayList<String>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AuthorizationService.isUserLogged(request);
			request.setAttribute("title", "Classement");
			request.setAttribute("joueur", "toto");
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ranking.jsp").forward(request, response);
		}catch(AuthorizationException e) {
			errors.add("Action non autorisée sans connexion !");
			request.setAttribute("errors", errors);
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

}
