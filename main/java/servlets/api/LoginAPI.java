package servlets.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
			
		
		
		var jsonO = getJSONObject(request.getInputStream());
		
		JSONObject obj = new JSONObject((Map) jsonO.get("user"));
		
		String email = (String)obj.get("email");
		String password = (String)obj.get("password");
			
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
	
	
	private JSONObject getJSONObject(ServletInputStream input) {
	
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(input));
			JSONParser jsonP = new JSONParser();
			return (JSONObject) jsonP.parse(rd);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
