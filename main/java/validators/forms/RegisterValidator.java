package validators.forms;

import javax.servlet.http.HttpServletRequest;

import DAO.DAOFactory;
import DAO.UserDAO;
import models.User;
import validators.Validator;

public class RegisterValidator extends Validator{
	
	public void verifierRegister( HttpServletRequest request) {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			UserDAO userDAO = (UserDAO) factory.getUserDAO();
			
			results.clear();
			
			var listPseudo = userDAO.getPseudos();
			var pseudo = request.getParameter("pseudo");
			
			//Verif du pseudo
			if(pseudo.isEmpty()) {
				results.add("Veuillez renseigner votre pseudo!");
			}
			else if(listPseudo.contains(pseudo)) {
				results.add("Votre pseudo a déjà été pris, veuillez changer!");
			}
			else {
				request.setAttribute("pseudo", pseudo);
			}
			
			var listEmails = userDAO.getEmails();
			
			//Verif du mail
			var email = request.getParameter("email");
			var emailConfirm = request.getParameter("emailConfirm");
			
			if(email.isEmpty()) {
				results.add("Veuillez renseigner votre email!");
			}
			else if(listEmails.contains(email)) {
				results.add("Votre email a déjà été pris, veuillez changer!");
			}
			else if(emailConfirm.isEmpty()) {
				results.add("Veuillez confirmer votre email!");
			}
			else if(!email.equals(emailConfirm)) {
				results.add("Votre email n'est pas confirmé!");
			}	
			else {
				request.setAttribute("email", email);
				request.setAttribute("emailConfirm", emailConfirm);
			}
			
			//Verif du mot de passe
			var password = request.getParameter("password");
			var passwordConfirm = request.getParameter("passwordConfirm");
					
			if(password.isEmpty()) {
				results.add("Veuillez renseigner votre mot de passe!");
			}
			else if(password.length()<8) {
				results.add("Votre mot de passe doit doit être au moins de 8 charactères!");
			}
			else if(passwordConfirm.isEmpty()) {
				results.add("Veuillez confirmer votre mot de passe!");
			}
			else if(!password.equals(passwordConfirm)) {
				results.add("Votre mot de passe n'est pas confirmé!");
			}	
			
			
			if(!results.isEmpty()) {
				request.setAttribute("errors", results);			
			}
			else {
				var newUser = new User(pseudo, email, password);
				userDAO.add(newUser);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errors", results);
		}
	}

}
