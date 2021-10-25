package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceTools {

	public static JSONObject serviceRefused(String message, int codeErreur) {
		JSONObject o = new JSONObject();
		try{
			o.put("Status", "KO");
			o.put("message", message);
			o.put("code", codeErreur);
		}catch(JSONException e) {
			e.printStackTrace();
		}
			return o;
	}
	public static JSONObject serviceAccepted() {
		JSONObject o = new JSONObject();
		try {
		o.put("Status", "OK");
		
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return o;
	}
	
	
}
