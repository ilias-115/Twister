package test;

import java.sql.Connection;
import java.util.Date;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import bd.Database;
import services.*;
import tools.FriendTools;
import tools.UserTools;

public class TestSQL {
	
	public static void main(String args[]) {
		Connection co = null;
		Statement st = null;
		ResultSet res = null;
	
		try {
			
			co = Database.getMySQLConnection();
			st = co.createStatement();
			String query = "SELECT * from login";
			res = st.executeQuery(query);
			
			System.out.println("ID_USER NOM PRENOM LOGIN ");
			while(res.next()) {
				String id_user = res.getString("id");
				String nom = res.getString("nom");
				String prenom = res.getString("prenom");
				String login = res.getString("login");
			
				System.out.println(id_user+" "+nom+" "+prenom+" "+" "+login+" ");
			}
		} catch (SQLException s) {
			System.out.println("In the exception");
			s.printStackTrace();
		} finally {
			try {
				res.close();
				st.close();
				co.close();
			} catch (SQLException ignore) {}
		}
		
	}
}	
	
