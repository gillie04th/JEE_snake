package validators.forms;

import javax.servlet.http.HttpServletRequest;

import DAO.DAOFactory;
import DAO.UserDAO;
import validators.Validator;

public class UnregisterValidator extends Validator{
	
	public void verifierUnregister(HttpServletRequest request) {
		var session = request.getSession();
		int id_joueur = (int)session.getAttribute("id");
		
		results.clear();
		
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = (UserDAO) factory.getUserDAO();
		
		var joueur = userDAO.get(id_joueur);
		
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
				userDAO.delete(id_joueur);
			}
		}
		request.setAttribute("errorsSuppr", results);	
	}
}