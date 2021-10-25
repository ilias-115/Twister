package services;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import tools.FriendTools;
import tools.ServiceTools;
import tools.UserTools;

public class User {

	/**
	 * Créé un utilisateur et l'ajoute a la table login
	 * verifie que les parametres sont ni null ni vide
	 * verifie si le login est deja utilisé 
	 * retourne un json vide si tout s'est bien passé 
	 */
	public static JSONObject createUser(String login , String password , String prenom , String nom) {
		if(login == null || password == null || prenom == null || nom == null) {
			return ServiceTools.serviceRefused("parametre(s) manquant(s)", 1);
		}
		if(login == "" || password == ""|| prenom == "" || nom == "") {
			return ServiceTools.serviceRefused("parametre(s) manquant(s)", 1);
		}
		if(UserTools.userExists(login)) {
			return ServiceTools.serviceRefused("login deja existant", 1);
		}
		
		return UserTools.insertUser(login , password , prenom , nom);
	}
		
	/**
	 * Connecte un user et l'ajoute a la table session
	 * verifie que les champs ne sont pas null
	 * verifie si le login existe, puis si le mdp est le bon
	 * retourne un json contenant la cle si tout s'est bien passé
	 */
	public static JSONObject login(String login, String password) throws JSONException {
		if(login == null || password == null) {
			return ServiceTools.serviceRefused("login / password manquants", 1);
		}
		if(!UserTools.userExists(login)) {
			return ServiceTools.serviceRefused("login non reconnu", 1);
		}if(!UserTools.checkPassword(login,password)) {
			return ServiceTools.serviceRefused("mot de passe erroné", 1);
		}	
		int id_user = UserTools.getIdUser(login);
		boolean connect_ok = UserTools.userConnect(id_user);
		if (connect_ok) {
			return ServiceTools.serviceRefused("User deja connecté", 3);	
		}
		String key = UserTools.insertConnexion(id_user,0);
		JSONObject res = ServiceTools.serviceAccepted();
		res.put("key", key);
		return res;
	}
	
	
	/**
	 * Déconnete un user en l'enlevant de la table session
	 * verifie si le parametre est null
	 * verifie si l'user est connecté 
	 * renvoie un json OK si tout s'est bien passé 
	 */
	public static JSONObject logout(String key) {
		if(key==null) {
			return ServiceTools.serviceRefused("cle manquante", 1);
		}
		if(!UserTools.userConnect(UserTools.getIdUserByKey(key))) {
			return ServiceTools.serviceRefused("User deja déconnecté", 1);
		}
		UserTools.removeConnexion(key);
		return ServiceTools.serviceAccepted();
	}			

}	
		
	
	
	
	
	
	

