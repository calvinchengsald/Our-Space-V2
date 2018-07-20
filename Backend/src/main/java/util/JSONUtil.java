package util;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class JSONUtil {

	
	public static JSONObject getObj(HttpServletRequest req) {

		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
		    BufferedReader reader = req.getReader();
		    while ((line = reader.readLine()) != null)
		    jb.append(line);
		} catch (Exception e) { e.printStackTrace(); }
		 	
		JSONObject obj = null;
		try {
			obj = new JSONObject(jb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
