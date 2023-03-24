package servlets.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOException;
import DAO.DAOFactory;
import DAO.GameDAO;
import models.Game;
import services.AuthorizationException;
import services.AuthorizationService;

/**
 * Servlet implementation class Home
 */
@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> errors;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        errors = new ArrayList<String>();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AuthorizationService.isUserLogged(request);
			request.setAttribute("title", "Accueil");
			
			DAOFactory factory = DAOFactory.getInstance();
			GameDAO gameDAO = (GameDAO) factory.getGameDAO();
			
			Game game = gameDAO.getBestGame(Integer.parseInt(request.getSession().getAttribute("id").toString()));
			request.setAttribute("game", game);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
		}catch(AuthorizationException e) {
			errors.add("Action non autorisée sans connexion !");
			request.setAttribute("errors", errors);
			response.sendRedirect(request.getContextPath() + "/login");			
		} catch (NumberFormatException | DAOException e) {
			System.out.println(e.getMessage());
		}
	}

}
