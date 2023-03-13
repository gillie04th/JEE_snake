package servlets.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonManagement {
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

}
