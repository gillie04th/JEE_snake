package validators.forms;

import validators.Validator;

public class LoginValidator extends Validator {
	
	public void validateLogin(String login, String password) {
		if(login == "" | login == null) {
			this.results.add("Aucun login n'est renseigné");
		}
		if(password == "" | password == null) {
			this.results.add("Le mot de passe n'est pas renseigné");
		}
	}
}