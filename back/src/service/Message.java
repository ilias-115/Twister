package service;

import org.json.JSONObject;

import tool.CheckTools;
import tool.MessageTools;
import tool.ReturnJSON;
import tool.UserTools;

public class Message {
	public Message() {}
	
	
	/**
	 * Service permettant de poster un message
	 * @param key
	 * @param text
	 * @return
	 */
	public static JSONObject postMessage(String key, String text) {
		if(!CheckTools.alreadyConnected(new StringBuilder(UserTools.getLoginUser(key))))
			return ReturnJSON.serviceRefused("USER DISCONNECTED", 401);
		
		return tool.MessageTools.postMessage(key, text);
		//return ReturnJSON.serviceAccepted();
	}
	
	/**
	 * Service pour lister les messages
	 * @param key
	 * @param query
	 * @param friends
	 * @return
	 */
	public static JSONObject listMessage(String key, String query ,String friends) {
		if(!CheckTools.alreadyConnected(new StringBuilder(UserTools.getLoginUser(key))))
			return ReturnJSON.serviceRefused("USER DISCONNECTED", 701);
		if(!query.isEmpty() && friends.isEmpty())
			return MessageTools.listByQuery(key,query);
		else if(!friends.isEmpty() && query.isEmpty())
			return MessageTools.listProfile(key,friends);
		else
			return MessageTools.listAllMessage(key);
	}
	
	/**
	 * Service pour enlever un message
	 * @param key
	 * @param id
	 * @return
	 */
	public static JSONObject removeMessage(String key,String id) {
		if(!CheckTools.alreadyConnected(new StringBuilder(UserTools.getLoginUser(key))))
			return ReturnJSON.serviceRefused("USER DISCONNECTED", 402);
		
		return MessageTools.removeMessage(id);
	}

	
}
