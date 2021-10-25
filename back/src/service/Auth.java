package service;

import org.json.JSONException;
import org.json.JSONObject;
import tools.*;
public class Auth {

	
	public Auth() {
	
	}
	/**
	 * Service permettant la connection
	 * @param login
	 * @param psswd
	 * @return
	 */
	public static JSONObject connexion(String login,String psswd,int root) {
		if(!tool.CheckTools.exist(login)) {
			return tool.ReturnJSON.serviceRefused("User not found",201);
		}
		
		if(!tool.CheckTools.checkPasswd(login,psswd)) {
			return tool.ReturnJSON.serviceRefused("Error Password",202);
		}
		
		StringBuilder key=new StringBuilder().append(tool.AuthTools.insertNvlleSession(new StringBuilder(login),root)); //Obtenir cle (qui expire)connexion
		
		if(key.length()==0) { //Si déjà connecter==longueur de la clée vide
			return tool.ReturnJSON.serviceRefused("Already Connected", 203);
		}
		else { //Sinon la connexion est reusssie
			JSONObject js= new JSONObject();
			try {
				js.put("Login", login);
				js.put("Key", key);
			} catch (JSONException e) {
				return tool.ReturnJSON.serviceRefused("JSON Error", 120);
			}
			return js;
		}
	}
	
	/**
	 * Service permettant la deconnection
	 * @param key
	 * @return
	 */
	public static JSONObject deconnexion(String key) {
		if(!tool.UserTools.connected(key))
			return tool.ReturnJSON.serviceRefused("User already disconnected", 301);
		tool.AuthTools.deconnexion(key);
		return tool.ReturnJSON.serviceAccepted();	
	}
}
