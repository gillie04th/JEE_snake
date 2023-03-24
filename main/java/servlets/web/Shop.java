package servlets.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOException;
import DAO.DAOFactory;
import DAO.UserDAO;
import services.AuthorizationException;
import services.AuthorizationService;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/shop")
public class Shop extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<String> errors;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() {
        super();
        this.errors = new ArrayList<String>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AuthorizationService.isUserLogged(request);
			request.setAttribute("title", "Boutique");
			
			// Récupération du chemin absolu du répertoire "images"
	        String imagePath = getServletContext().getRealPath("/images/skins");
	        File imageDir = new File(imagePath);
	        
	        // Liste des fichiers dans le répertoire "images"
	        File[] files = imageDir.listFiles();
	        ArrayList<String> fileNames = new ArrayList<String>();
	        for (File file : files) {
	            if (file.isFile()) {
	            	System.out.println(file.getName());
	                fileNames.add(file.getName());
	            }
	        }
	        request.setAttribute("imageFiles", fileNames);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/shop.jsp").forward(request, response);
		}catch(AuthorizationException e) {
			errors.add("Action non autorisée sans connexion !");
			request.setAttribute("errors", errors);
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			UserDAO userDAO = (UserDAO) factory.getUserDAO();
		
			userDAO.setSkin(Integer.parseInt(request.getSession().getAttribute("id").toString()), request.getParameter("skin").toString());
			
			doGet(request, response);
		} catch (NumberFormatException | DAOException e) {
			System.out.println(e.getMessage());
		}
	}

}
