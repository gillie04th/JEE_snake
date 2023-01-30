package validators.forms;

import javax.servlet.http.HttpServletRequest;

import DAO.DAOFactory;
import DAO.UserDAO;
import models.User;
import validators.Validator;

public class LoginValidator extends Validator {
	
	public void validateLogin(HttpServletRequest request) {
		String login = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(login != "" & password != "") {
			UserDAO dao = (UserDAO) DAOFactory.getInstance().getUserDAO();
			User user = dao.isUserRegistered(login, password);
			if(user != null) {
				request.getSession().setAttribute("user", user);
			} else {
				request.setAttribute("email", request.getParameter("email"));
				this.results.add("Les identifiants fournis sont incorrects");
			}
		}else {
			this.results.add("Le login et/ou le mot de passe ne sont pas renseignés");
		}
	}
}