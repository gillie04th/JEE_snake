package validators.forms;

import javax.servlet.http.HttpServletRequest;

import DAO.DAOException;
import DAO.DAOFactory;
import DAO.UserDAO;
import models.User;
import validators.Validator;

public class UnregisterValidator extends Validator{
	
	public void verifierUnregister(HttpServletRequest request) {
		var session = request.getSession();
		int id_joueur = (int)session.getAttribute("id");
		
		results.clear();
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = (UserDAO) factory.getUserDAO();
		
		User joueur = new User();
		try {
			joueur = userDAO.get(id_joueur);
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(joueur == null) {
			results.add("Erreur, joueur introuvable!");
		}
		else {
			var password = request.getParameter("passwordSuppr");
			
			if(!password.equals(joueur.getPassword())) {
				results.add("Mot de passe incorrect!");
			}
			else {
				request.setAttribute("pseudo", joueur.getName());
				try {
					userDAO.delete(id_joueur);
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				session.invalidate();
			}
		}
	}
}