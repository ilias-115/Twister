package tool;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONException;
import org.json.JSONObject;

public class UserTools {

	/**
	 * créer un user dans la BD
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param psswd
	 * @param mail
	 * @return
	 */
	public static JSONObject insertUser(String nom, String prenom, String login, String psswd, String mail) {
		try {
			Connection conn= Database.getMySQLConnection();
			String query="INSERT INTO user VALUES ('"+login+"','"+psswd+"','"+prenom+"','"+nom+"','"+mail+"')";
			//System.out.println(query);
			Statement st=conn.createStatement();
			int rs=st.executeUpdate(query);
			st.close();
			conn.close();
			if(rs==0)
				return tool.ReturnJSON.serviceRefused("Failed creating user",103);
			return tool.ReturnJSON.serviceAccepted();
		}
		catch (SQLException s) {
			return tool.ReturnJSON.serviceRefused("SQL ERROR",110);
		}
	}
	
	/**
	 * Permet de se connecter
	 * @param key
	 * @return
	 */
	public static boolean connected(String key) {
		try {
			Connection conn= Database.getMySQLConnection();
			
			String query="SELECT * FROM session WHERE session.key_user='"+key+"'";
			//System.out.println(query);
			
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			
			if(rs.next()) { //si user existe dans la base de données session => il est déjà connecté
				//if(rs.getString("key_user")!=null) //Si la clée n'est pas null, user déjà connecté
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			return false;
		}
		catch (SQLException s) {
			//System.out.println("herE"); // a enlever après test
			System.out.println(tool.ReturnJSON.serviceRefused("SQL ERROR", 110));
			return false;
		}
	}

	/**
	 * Ajoute un ami
	 * @param key
	 * @param logFriend
	 */
	public static void AddFriend(String key, String logFriend) {

		try {		
			Connection conn= Database.getMySQLConnection();
			String loginUser=tool.UserTools.getLoginUser(key);
			
	
			
			//Date courante
			
			String date =getDate(); 
			
			//Requete
			
			String query="INSERT INTO friends VALUES ('"+loginUser+"','"+logFriend+"','"+date+"')";
			//System.out.println(query);
			Statement st=conn.createStatement();
			st.executeUpdate(query);

			st.close();
			conn.close();
			
		}
		catch (SQLException s) {
			//System.out.println("ADDFRIENDERROR"); // a enlever après test
			System.out.println(tool.ReturnJSON.serviceRefused("SQL ERROR", 110));
			
		}
	}
	/**
	 * Supprime un ami
	 * @param key
	 * @param logFriend
	 */
	public static void RemoveFriend(String key, String logFriend) {

		try {
			Connection conn= Database.getMySQLConnection();
			String loginUser=tool.UserTools.getLoginUser(key);
			
			//Requete
			
			String query="DELETE FROM friends WHERE friends.log_user='"+loginUser+"' AND friends.log_friend='"+logFriend+"'";
			//System.out.println(query);
			Statement st=conn.createStatement();
			st.executeUpdate(query);

			st.close();
			conn.close();
			
		}
		catch (SQLException s) {
			//System.out.println("ADDFRIENDERROR"); // a enlever après test
			System.out.println(tool.ReturnJSON.serviceRefused("SQL ERROR", 110));
			
		}
	}
	
	/**
	 * Retourne la date actuelle
	 * @return
	 */
	public static String getDate() {
		Calendar call=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String date =sdf.format(call.getTime()); 
		return date;
	}
	
	/**
	 * Get le login d'un user en fonction de sa key
	 * @param key
	 * @return
	 */
	public static String getLoginUser(String key) {//A OPTIMISER
		try {
			
			String res="";
			
			Connection conn= Database.getMySQLConnection();
			String query="SELECT * FROM session WHERE session.key_user='"+key+"'";
			
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			if(rs.next()) { //Probleme si plusieurs login mais login unique normalement
				res=rs.getString("login");
			}
			rs.close();
			st.close();
			conn.close();
			return res;
		}
		catch (SQLException s) {
			System.out.println(tool.ReturnJSON.serviceRefused("SQL ERROR", 110));
			return "";
		}
		
	}

	public static JSONObject listFriend(String log) {
		try {
			JSONObject res= new JSONObject();			
			
			Connection conn= Database.getMySQLConnection();
			String query="SELECT * FROM friends WHERE friends.log_user='"+log+"'";
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) { 
				res.append("friends",new JSONObject().put("friend",rs.getString("log_friend"))); //ajout de chaque amis de "log" dans la liste 
			}
			
			rs.close();
			st.close();
			conn.close();
			return res;
		}
		catch (SQLException s) {
			return ReturnJSON.serviceRefused("SQL ERROR", 110);
		}
		catch (JSONException e) {
			return ReturnJSON.serviceRefused("JSON ERROR", 120);
		}
		
	}

	public static JSONObject listCo() {
		try {
			JSONObject res= new JSONObject();			
			
			Connection conn= Database.getMySQLConnection();
			String query="SELECT * FROM session";
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) { 
				res.append("connected",new JSONObject().put("guy",rs.getString("login"))); //ajout de chaque amis de "log" dans la liste 
			}
			
			rs.close();
			st.close();
			conn.close();
			System.out.println(res);
			return res;
		}
		catch (SQLException s) {
			return ReturnJSON.serviceRefused("SQL ERROR", 110);
		}
		catch (JSONException e) {
			return ReturnJSON.serviceRefused("JSON ERROR", 120);
		}
	}
	
}
	
