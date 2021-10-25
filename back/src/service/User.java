package service;

import org.json.JSONObject;

import tool.CheckTools;
import tool.ReturnJSON;
import tool.UserTools;

public class User {
	
	/**
	 * Service permettant la creation d'un utilisateur
	 * @param nom
	 * @param prenom
	 * @param login
	 * @param psswd
	 * @param mail
	 * @return
	 */
	public static JSONObject createUser(String nom, String prenom, String login, String psswd, String mail) {
		
		if((login==null)||(psswd==null)||(login.equals("")||(psswd.equals("")))||(prenom==null)||(nom==null)||(prenom.equals("")||(nom.equals("")||mail.equals("")||mail==null))) {
			return (ReturnJSON.serviceRefused("Error Argument",101));
		}
		if(tool.CheckTools.exist(login))
			return (ReturnJSON.serviceRefused("user already exist",102));
		
		return tool.UserTools.insertUser(nom, prenom, login, psswd, mail);

	}

	public static JSONObject listCo(String key) {
		if(!CheckTools.alreadyConnected(new StringBuilder(UserTools.getLoginUser(key))))
			return ReturnJSON.serviceRefused("User Disconnected", 501);

		return UserTools.listCo();
	}
}
