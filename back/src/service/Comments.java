package service;

import org.json.JSONObject;

import tool.CheckTools;
import tool.MessageTools;
import tool.ReturnJSON;

public class Comments {
	public Comments() {}
	
	
	/**
	 * Service permettant de poster un commentaire
	 * @param login
	 * @param id_message
	 * @param text
	 * @return
	 */
	public static JSONObject postComment(String login,String id_message,String text) {
		if(!tool.CheckTools.alreadyConnected(new StringBuilder(login)))
			return tool.ReturnJSON.serviceRefused("USER DISCONNECTED", 801);
		
		tool.MessageTools.addComment(login, id_message, text);
		return tool.ReturnJSON.serviceAccepted();
	}
	
	/**
	 * Service pour retirer un commentaire
	 * @param login
	 * @param id_message
	 * @param id_comment
	 * @return
	 */
	public static JSONObject removeComment(String login,String id_message,String id_comment) {
		if(!tool.CheckTools.alreadyConnected(new StringBuilder(login)))
			return tool.ReturnJSON.serviceRefused("USER DISCONNECTED", 802);
		
		return tool.MessageTools.removeComment(login, id_message, id_comment);
	}

	
}
