package servlets.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import validators.forms.UnregisterValidator;

/**
 * Servlet implementation class Unregister
 */
@WebServlet("/unregister")
public class Unregister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Unregister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("title", "Désinscription");
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/unregister.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UnregisterValidator unregisterValidator = new UnregisterValidator();
		
		unregisterValidator.verifierUnregister(request);
		
		var results = unregisterValidator.getResults();
		
		if(!results.isEmpty()) {
			request.setAttribute("errorsSuppr", results);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(request, response);		
		}
		else {
			doGet(request, response);
		}
	}

}