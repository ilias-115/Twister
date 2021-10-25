package test;
import services.User;
import tools.UserTools;
import tools.ServiceTools;
import org.json.JSONException;
import org.json.JSONObject;

public class TestUser {

	public static void main(String [] args) throws JSONException {
		
		// Test sur createUser
		JSONObject c1 = User.createUser("ahmed","lol","ahmed","rachid");
		JSONObject c2 = User.createUser(null,null,null,null);
		System.out.println(c1);
		System.out.println(c2);
		
		// Test sur login
		JSONObject l1 = User.login("ahmed", "lol");
		JSONObject l2 = User.login(null, null);
		System.out.println(l1);
		System.out.println(l2);
		
		// Test sur logout
		JSONObject r1 = User.logout(UserTools.getKeyById(1));
		System.out.println(r1);
		JSONObject r2 = User.logout(null);
		System.out.println(r2);
		
	}
	
	
	
}
