package servlets.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import models.User;

public class JsonUtils {
	public static JSONObject getJSONObject(ServletInputStream input) {
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
	
	public static User jsonToUser(String data) {
		User user = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			user = mapper.readValue(data, User.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
