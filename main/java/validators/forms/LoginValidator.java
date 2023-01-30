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
				request.getSession().setAttribute("login", user.getEmail());
				request.getSession().setAttribute("name", user.getName());
				request.getSession().setAttribute("id", user.getId());
			} else {
				request.setAttribute("email", request.getParameter("email"));
				this.results.add("Les identifiants fournis sont incorrects");
			}
		}else {
			this.results.add("Le login et le mot de passe ne sont pas renseignés");
		}
	}
}