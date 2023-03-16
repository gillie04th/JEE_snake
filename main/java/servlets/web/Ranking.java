package servlets.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
			
			DAOFactory factory = DAOFactory.getInstance();
			GameDAO gameDAO = (GameDAO) factory.getGameDAO();
			
			List<Game> games = gameDAO.getAll();
			Collections.sort(games);
			request.setAttribute("games", games);
			
			List<String> mapsName = gameDAO.getAllMapName();
			request.setAttribute("mapsName", mapsName);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/ranking.jsp").forward(request, response);
		}catch(AuthorizationException e) {
			errors.add("Action non autorisée sans connexion !");
			request.setAttribute("errors", errors);
			response.sendRedirect(request.getContextPath() + "/login");
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
