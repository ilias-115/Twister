package services;

import org.json.JSONObject;

import tools.FriendTools;
import tools.ServiceTools;
import tools.UserTools;

public class Friends {

	/**
	 * Renvoie la liste des amis de la personne possedant la clef key
	 * verifie si key est null
	 * verifie le celui qui veut voir la liste est connecté
	 */
	public static JSONObject listFriends(String key) {
		if(key==null) {
			return ServiceTools.serviceRefused("Paramètre(s) vide(s)", -1);
		}
		if(!UserTools.userConnect(key)) {
			return ServiceTools.serviceRefused("User non connecte", -1);
		}
		return FriendTools.listFriends(key);
	}
	/**
	 *Ajoute un ami dans la table friends 
	 *verifie si key est null
	 *verifie si l'utilisateur que l'on veut ajouter existe
	 *verifie le celui qui veut ajouter la personne est connecté
	 *verifie si celui qui veut ajouter la personne ne l'a pas deja ajouté
	 *renvoie un json contenant OK si tout s'est bien passé 
	 */
	public static JSONObject addFriend(String key, int idAdd) {
		if(key==null) {
			return ServiceTools.serviceRefused("Paramètre(s) vide(s)", -1);
		}
		if(!UserTools.userConnect(UserTools.getIdUserByKey(key))) {
			return ServiceTools.serviceRefused("User déconnecté", -1);
		}
		if(!UserTools.userExists(idAdd)) {
			return ServiceTools.serviceRefused("l'utilisateur que vous voulez ajouter n'existe pas", -1);
		}

		boolean isFriend = FriendTools.isFriend(key, idAdd);
		if(isFriend) {
			return ServiceTools.serviceRefused("User deja ami", -1);
		}
		return FriendTools.insertFriend(key,idAdd);
	}
	
	/**
	 *enleve un ami dans la table friends 
	 *verifie si key est null
	 *verifie le celui qui veut ajouter la personne est connecté
	 *verifie si celui qui veut enlever la personne ne l'a pas deja enlevée 
	 *renvoie un json contenant OK si tout s'est bien passé 
	 */
	public static JSONObject deleteFriend(String key,int idDelete) {
		if(key==null) {
			return ServiceTools.serviceRefused("Paramètre(s) vide(s)", -1);
		}
		if(!UserTools.userConnect(UserTools.getIdUserByKey(key))) {
			return ServiceTools.serviceRefused("User déconnecté", -1);
		}
		boolean isFriend = FriendTools.isFriend(key, idDelete);
		if(!isFriend) {
			return ServiceTools.serviceRefused("User non ami", -1);
		}
		return FriendTools.removeFriend(key,idDelete);
	}
	
}
