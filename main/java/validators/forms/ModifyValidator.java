package validators.forms;

import javax.servlet.http.HttpServletRequest;

import DAO.DAOFactory;
import DAO.UserDAO;
import validators.Validator;

public class ModifyValidator extends Validator{
	
	public void verifierModify( HttpServletRequest request) {
		try {
			results.clear();
			
			var session = request.getSession();
			int id_joueur = (int)session.getAttribute("id");
			
			DAOFactory factory = DAOFactory.getInstance();
			UserDAO userDAO = (UserDAO) factory.getUserDAO();
			
			var joueur = userDAO.get(id_joueur);
			
			if(joueur == null) {
				results.add("Erreur, joueur introuvable!");
			}
			else {
				var password = request.getParameter("password");
				
				if(!password.equals(joueur.getPassword())) {
					results.add("Mot de passe actuel incorrect!");
				}
				else {
					
					var pseudo = request.getParameter("name");
					
					//Regarde si le pseudo a été changé
					if(!pseudo.equals(joueur.getName())) {
						var listPseudo = userDAO.getPseudos();
						//Verif du pseudo
						if(pseudo.isEmpty()) {
							results.add("Veuillez renseigner votre pseudo!");
						}
						else if(listPseudo.contains(pseudo)) {
							results.add("Votre pseudo a déjà été pris, veuillez changer!");
						}
						else {
							//request.setAttribute("pseudo", pseudo);
							joueur.setName(pseudo);
						}
					}
					
					
					var email = request.getParameter("email");
					
					//Regarde si le mail a été changé
					if(!email.equals(joueur.getEmail())) {
						var listEmails = userDAO.getEmails();
						//Verif du mail
						if(email.isEmpty()) {
							results.add("Veuillez renseigner votre email!");
						}
						else if(listEmails.contains(email)) {
							results.add("Votre email a déjà été pris, veuillez changer!");
						}
						else {
							//request.setAttribute("pseudo", pseudo);
							joueur.setEmail(email);
						}
					}
					
					
					var newPassword = request.getParameter("newPassword");
					var newPasswordConfirm = request.getParameter("confirmNewPassword");
					
					//Regarde si un nouveau mot de passe est renseigné
					if(!newPassword.isEmpty()) {
						//Verif du mot de passe
						if(newPassword.length()<8) {
							results.add("Votre nouveau mot de passe doit être au moins de 8 charactères!");
						}
						else if(newPasswordConfirm.isEmpty()) {
							results.add("Veuillez confirmer votre nouveau mot de passe!");
						}
						else if(!newPassword.equals(newPasswordConfirm)) {
							results.add("Votre nouveau mot de passe n'est pas confirmé!");
						}
						else {
							joueur.setPassword(newPassword);
						}
					}				
				}
			}
			
			//Si pas d'erreur on met à jour les données du joueur sinon on affiche les erreurs
			if(!results.isEmpty()) {
				request.setAttribute("errors", results);			
			}
			else {
				userDAO.update(joueur);
				request.getSession().setAttribute("login", joueur.getEmail());
				request.getSession().setAttribute("name", joueur.getName());
				request.setAttribute("validate", true);	
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errors", results);
		}
		
	}

}