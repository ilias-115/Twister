package services;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import tools.MessagesTools;
import tools.ServiceTools;

public class Messages {
	/**
	 * Renvoie la liste des messages entre deux personnes dans un json 
	 */
	public static JSONObject getMessages(String key, int idFriend) {
		JSONObject list=new JSONObject();
		if((key==null)||(idFriend==-1)) {
			return ServiceTools.serviceRefused("message error", 1);
		}
		try {
			list=MessagesTools.listMessages(key, idFriend);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	public static JSONObject getMessages(String key) {
		JSONObject list=new JSONObject();
		if(key==null) {
			return ServiceTools.serviceRefused("message error", 1);
		}
		try {
			list=MessagesTools.listMessages(key);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}
	/**
	 * Ajoute un message avec un idSource et un idDest
	 * renvoie le message envoyé dans un json
	 */
	public static JSONObject addMessage(String key, int idFriend, String message) {
		if((key==null)||(idFriend==-1)||(message==null)) {
			return ServiceTools.serviceRefused("login error", 1);
		}
		try {
			MessagesTools.sendMessage(key, idFriend, message);
		} catch (SQLException e1) {
			
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("Message", message);
		}catch(JSONException e) {
			return ServiceTools.serviceRefused("Erreur SQL", 1000);
		}
		return obj;
	}
	
	/**
	 * Supprime un message de la bdd grace a son identifiant
	 * renvoie un json "ok" si tout s'est bien passé  
	 */
	public static JSONObject deleteMessage(int idMessage) {
		if(idMessage==-1) {
			return ServiceTools.serviceRefused("login error", 1);
		}
		try {
			MessagesTools.deleteMessage(idMessage);
		} catch (SQLException e) {
			return ServiceTools.serviceRefused("Erreur SQL", 1000);
		}
		return ServiceTools.serviceAccepted();
	}
	/**
	 * Ajoute un message avec un idSource qui est destiné à tout le monde
	 * renvoie le message envoyé dans un json
	 */
	public static JSONObject postMessage(String key, String message) {
		if((key==null)||(message==null)) {
			return ServiceTools.serviceRefused("login error", 1);
		}
		try {
			MessagesTools.postMessage(key, message);
		} catch (SQLException e1) {	
		}
		JSONObject obj = new JSONObject();
		try {
			obj.put("Message", message);
		}catch(JSONException e) {
			return ServiceTools.serviceRefused("Erreur SQL", 1000);
		}
		return obj;
	}
	
	/**
	 * Ne fais rien pour l'instant
	 */
	public static JSONObject searchMessage(String key, String message) {
		if((key==null)||(message==null)) {
			return ServiceTools.serviceRefused("message error", 1);
		}
		ArrayList<String> list;
		list=MessagesTools.searchMessages(key, message);
		JSONObject obj = new JSONObject();
		try {
			obj.put("listeMessages", list);
		}catch(JSONException e) {
			e.printStackTrace();
		}
		return obj;	
	}
	
	
	public static JSONObject postComment(String key, int idMessage, String commentaire) {
		if(key == null || idMessage == 0 || commentaire == null) {
			return ServiceTools.serviceRefused("parametres manquants", 1);
		}
		JSONObject o = new JSONObject();
		try {
			MessagesTools.postComment(key, idMessage, commentaire);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			o.put("commentaires : ",commentaire);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return o;
	}
	
}
