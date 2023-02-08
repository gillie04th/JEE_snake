package validators.forms;

import javax.servlet.http.HttpServletRequest;

import DAO.DAOException;
import DAO.DAOFactory;
import DAO.UserDAO;
import models.User;
import validators.Validator;

public class LoginValidator extends Validator {
	
	public void validateLogin(HttpServletRequest request) {
		
		try {
			String login = request.getParameter("email");
			String password = request.getParameter("password");
			
			if(login != "" & password != "") {
				UserDAO dao = (UserDAO) DAOFactory.getInstance().getUserDAO();
				User user = dao.isUserRegistered(login, password);
				if(user != null) {
					request.getSession().setAttribute("login", user.getEmail());
					request.getSession().setAttribute("name", user.getName());
					request.getSession().setAttribute("id", user.getId());
				} else {
					request.setAttribute("email", request.getParameter("email"));
					this.results.add("Les identifiants fournis sont incorrects");
				}
			}else {
				this.results.add("Le login et/ou le mot de passe ne sont pas renseignés");
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	
	public User validateLoginAPI(String email, String password) {
		

		try {
			if(email != null & password != null) {
				UserDAO dao = (UserDAO) DAOFactory.getInstance().getUserDAO();
				User user = dao.isUserRegistered(email, password);
				if(user == null) {
					this.results.add("Les identifiants fournis sont incorrects");
				}
				else {
					return user;
				}
			}else {
				if(email == null & password == null) {
					this.results.add("Le login et le mot de passe ne sont pas renseignés");
				}
				else if(email == null) {
					this.results.add("Le login n'est pas renseigné");
				}
				else if(password == null) {
					this.results.add("Le mot de passe n'est pas renseigné");
				}
				
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}