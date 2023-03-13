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
 * Servlet implementation class MapAPI
 */
@WebServlet("/api/map")
public class MapAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var jsonO = JsonManagement.getJSONObject(request.getInputStream());
		
		String email = (String)jsonO.get("email");
		String password = (String)jsonO.get("password");
			
		LoginValidator validator = new LoginValidator();
		User user = validator.validateLoginAPI(email, password);
			
		HashMap<String,Object> hashMap  = new HashMap<String, Object>();
			
		if(user == null) {
			hashMap.put("status_code", 401);
			hashMap.put("message", validator.getResults());
		}
		else {
			hashMap.put("status_code", 200);
			hashMap.put("message", "Connexion réussi");
			hashMap.put("user", user);
		}
			
		var res = new ObjectMapper().writeValueAsString(hashMap);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(res);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
