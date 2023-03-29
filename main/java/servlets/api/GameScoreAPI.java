package servlets.api;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.DAOException;
import DAO.DAOFactory;
import DAO.GameDAO;
import models.Game;
import models.User;

/**
 * Servlet implementation class GameScoreAPI
 */
@WebServlet("/api/game/score")
public class GameScoreAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameScoreAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String,Object> hashMap  = new HashMap<String, Object>();
		
		hashMap.put("status_code", 200);
		
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
		
		JSONObject jsonO = (JSONObject) JsonUtils.getJSONObject(request.getInputStream());
		
		String layout = (String)((JSONObject)jsonO).get("layout");
		long maxTurn = (long)((JSONObject)jsonO).get("maxTurn");
		long turns = (long)((JSONObject)jsonO).get("turn");
		long time = (long)((JSONObject)jsonO).get("time");
		String message = (String)((JSONObject)jsonO).get("message");
		User user = JsonUtils.jsonToUser(((JSONObject)jsonO).get("user").toString());
		String timestamp = (String)((JSONObject)jsonO).get("timestamp");
		long score = (long)((JSONObject)jsonO).get("score");
		
		
		Game game = new Game();
		game.setMap(layout);
		game.setJoueur(user);
		game.setDepart(timestamp);
		game.setSpeed((int) time);
		game.setTours((int) turns);
		game.setToursMax((int) maxTurn);
		game.setStatus(message);
		game.setScore((int) score);
		
		
			
		//GameValidator validator = new LoginValidator();
		//User user = validator.validateLoginAPI(email, password);
		
		DAOFactory factory = DAOFactory.getInstance();
		GameDAO gameDAO = (GameDAO) factory.getGameDAO();
		
		try {
			gameDAO.add(game, user);
		} catch (DAOException e) {
			e.printStackTrace();
		}
			
		HashMap<String,Object> hashMap  = new HashMap<String, Object>();
		
		if(false) {
			hashMap.put("status_code", 401);
			//hashMap.put("message", validator.getResults());
		}
		else {
			hashMap.put("message", "Connexion réussi");
			hashMap.put("user", "test");
		}
		
		hashMap.put("status_code", 200);
			
		var res = new ObjectMapper().writeValueAsString(hashMap);

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonO);
		out.flush();
	}

}
