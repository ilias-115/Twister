package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import bd.Database;

public class FriendTools {
	

	public static boolean isFriend(String key, int idFriend) {
		Connection co = null;
		Statement st = null;
		int idUser = UserTools.getIdUserByKey(key);
		ResultSet res = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select * from friends where id_user1 = '" +idUser +"' and id_user2 = '"+idFriend+"'";
			res = st.executeQuery(query);
			if(res.next()) {
				return true;
			}
			res.close();
			st.close();
			co.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	public static JSONObject insertFriend(String key, int idFriend) {

		Connection co = null;
		Statement st = null;
		int idUser = UserTools.getIdUserByKey(key);
		String prenomAdd = UserTools.getPrenomByIdUser(idFriend);
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "INSERT INTO friends VALUES('" + idUser + "','" + idFriend + "','" + new Timestamp(System.currentTimeMillis()) + "','" + prenomAdd +"')";	
			st.executeUpdate(query);
			st.close();
			co.close();
			return ServiceTools.serviceAccepted();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ServiceTools.serviceRefused("SQLException", 1000);
	}
	public static JSONObject removeFriend(String key, int idDelete) {
		Connection co = null;
		Statement st = null;
		int idUser = UserTools.getIdUserByKey(key);
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "delete from friends where id_user1='"+idUser+"' and id_user2='"+idDelete+"'";	
			st.executeUpdate(query);
			st.close();
			co.close();
			return ServiceTools.serviceAccepted();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ServiceTools.serviceRefused("SQLException", 1000);
	}
	public static JSONObject listFriends(String key) {
	
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		int idUser=UserTools.getIdUserByKey(key);
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select l.nom,l.prenom from friends f,login l where f.id_user1='"+idUser+"' and f.id_user2=l.id";	
			res = st.executeQuery(query);
			List<String> friends = new ArrayList<String>();
			while(res.next()) {
				String nom = res.getString("nom");
				String prenom = res.getString("prenom");
				friends.add(nom + " " + prenom);
			}
			JSONObject obj = ServiceTools.serviceAccepted();
			obj.put("friends", friends);
			res.close();
			st.close();
			co.close();
			return obj;
		}catch (SQLException s) {
			s.printStackTrace();
			return ServiceTools.serviceRefused("SQLException", 1000);
		} catch (JSONException j) {
			j.printStackTrace();
			return ServiceTools.serviceRefused("JSONException", 100);	
		}
	}
}
