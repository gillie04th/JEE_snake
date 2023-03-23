package servlets.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.User;
import validators.forms.LoginValidator;

/**
 * Servlet implementation class LoginAPI
 */
@WebServlet("/api/login")
public class LoginAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAPI() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		var jsonO = JsonUtils.getJSONObject(request.getInputStream());
		
		String email = (String)jsonO.get("email");
		String password = (String)jsonO.get("password");
			
		LoginValidator validator = new LoginValidator();
		User user = validator.validateLoginAPI(email, password);
			
		HashMap<String,Object> hashMap  = new HashMap<String, Object>();
			
		if(user == null) {
			hashMap.put("statusCode", 401);
			hashMap.put("message", validator.getResults());
		}
		else {
			hashMap.put("statusCode", 200);
			hashMap.put("message", "Connexion réussi");
			user.setToken("token_api");
			hashMap.put("user", user);
		}
			
		var res = new ObjectMapper().writeValueAsString(hashMap);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(res);
		out.flush();
	}
}
