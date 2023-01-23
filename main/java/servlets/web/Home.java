package servlets.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("joueur") != null) {
			
			request.setAttribute("title", "Accueil");
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
			
		} else {
			
			request.setAttribute("unauthorized", true);
			
			response.sendRedirect(request.getContextPath() + "/login");
			
		}
		
	}

}
