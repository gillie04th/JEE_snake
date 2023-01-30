package services;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationService {
	
	public static void isUserLogged(HttpServletRequest request) throws AuthorizationException {
		if(request.getSession().getAttribute("id") == null) {
			throw new AuthorizationException("L'action n'est pas autorisée sans identification");
		}
	}
}