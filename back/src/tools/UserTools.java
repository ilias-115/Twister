package tools;

import java.sql.*;
import java.util.Random;

import org.json.JSONObject;

import bd.Database;

public class UserTools {
	public static JSONObject insertUser(String login , String pwd , String prenom , String nom) {

		Connection co = null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "INSERT INTO login VALUES(null,'" + login + "','" + pwd + "','" + prenom + "','" + nom + "')";	
			st.executeUpdate(query);
			st.close();
			co.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
		
		
		
	}
	public static boolean userExists(String login) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select * from login where login = '" +login +"'";
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
	
	public static boolean userExists(int idUser) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select * from login where id = '" +idUser +"'";
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
	public static boolean checkPassword(String login, String password) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select login,password from login where login = '" +login +"' and password = '"+password+"'";
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
	
	public static String insertConnexion(int idUser, int b) {
		String key = "";
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for(int i=0;i<32;i++) {
			key+= chars.charAt(new Random().nextInt(chars.length()));
		}
		Connection co = null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "INSERT INTO sessions VALUES('"+key+"','"+idUser+"','" +new Timestamp(System.currentTimeMillis()) + "','" + b +"')";	
			st.executeUpdate(query);
			st.close();
			co.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return key;
		
	}
	public static void removeConnexion(String key) {
		Connection co = null;
		Statement st = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "delete from sessions where cle = '"+key+"'";	
			st.executeUpdate(query);
			st.close();
			co.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}

	public static boolean userConnect(int idUser) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select * from sessions where id = '" +idUser +"'";
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
	
	public static boolean userConnect(String key) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select * from sessions where cle = '" +key +"'";
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
	public static int getIdUser(String login) { //OK
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		int idUser = 0;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select id from login where login = '" +login +"'";
			res = st.executeQuery(query);
			if(res.next()) {
				idUser=res.getInt("id");
			}
			res.close();
			st.close();
			co.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return idUser;
	}
	public static int getIdUserByKey(String key) { //OK
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		int idUser = 0;
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select id from sessions where cle = '" +key +"'";
			res = st.executeQuery(query);
			if(res.next()) {
				idUser=res.getInt("id");
			}
			res.close();
			st.close();
			co.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	return idUser;
	}
	
	public static String getKeyById(int id) { //OK
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		String  key="";
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select cle from sessions where id = '" +id +"'";
			res = st.executeQuery(query);
			if(res.next()) {
				key=res.getString("cle");
			}
			res.close();
			st.close();
			co.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	return key;
	}
	
	public static void connexionUpdate() throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 1800000);
        try {
            Connection connexion = (Connection) Database.getMySQLConnection();
            String sql = "DELETE FROM sessions where timestamp < ?";
            PreparedStatement ps = (PreparedStatement) connexion.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE);
            ps.setTimestamp(1, timestamp);
            ps.executeUpdate();
            ps.close();
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static String getPrenomByIdUser(int id) { //OK
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
		String prenom = "";
		try {
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "select prenom from login where id = '" +id +"'";
			res = st.executeQuery(query);
			if(res.next()) {
				prenom=res.getString("prenom");
			}
			res.close();
			st.close();
			co.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	return prenom;
	}
}
