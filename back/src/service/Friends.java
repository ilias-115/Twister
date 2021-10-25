package service;

import org.json.JSONObject;

import tool.CheckTools;
import tool.ReturnJSON;
import tool.UserTools;

public class Friends {
	
	
	/**
	 * Service permettant d'ajouter un ami
	 * @param key
	 * @param logFriend
	 * @return
	 */
	public static JSONObject addFriend(String key,String logFriend) { 
		
		if(!CheckTools.alreadyConnected(new StringBuilder(UserTools.getLoginUser(key))))
			return ReturnJSON.serviceRefused("User Disconnected", 501);
		
		if(!CheckTools.exist(logFriend)) 
			return ReturnJSON.serviceRefused("Friend not exist", 502);

		if(CheckTools.isFriend(key, logFriend)) 
			return ReturnJSON.serviceRefused("Already friends", 503);
		
			
		//System.out.println("JE SUIS ARRIVER");
		tool.UserTools.AddFriend(key,logFriend);
			
		return ReturnJSON.serviceAccepted();
		
	}
	
	/**
	 * Service permettant de supprimer un ami
	 * @param key
	 * @param logFriend
	 * @return
	 */
	public static JSONObject removeFriend(String key,String logFriend) { 
		
		if(!CheckTools.alreadyConnected(new StringBuilder(UserTools.getLoginUser(key)))) {
			return ReturnJSON.serviceRefused("User Disconnected", 601);
		}
		
		if(!CheckTools.exist(logFriend)) {
			return ReturnJSON.serviceRefused("Friend not exist", 602);
		}
		
		if(!CheckTools.isFriend(key,logFriend)) {
			return ReturnJSON.serviceRefused("NOT Friend", 603);
		}
		System.out.println("JE SUIS ARRIVER");
		tool.UserTools.RemoveFriend(key,logFriend);
			
		return ReturnJSON.serviceAccepted();
		
	}
	
	public static JSONObject listFriend(String log) {
		
		if(!CheckTools.alreadyConnected(new StringBuilder(log))) {
			return ReturnJSON.serviceRefused("User not connected", 1001);
		}
		return tool.UserTools.listFriend(log);
		
	}
}
