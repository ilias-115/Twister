package test;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friends;
import services.User;
import tools.FriendTools;
import tools.UserTools;

public class TestFriend {
	public static void main(String [] args) throws JSONException {
		
		JSONObject u1 = User.createUser("user1", "user1", "user1", "user1");
		JSONObject u2 = User.createUser("user2", "user2", "user2", "user2");
		JSONObject u3 = User.createUser("user3", "user3", "user3", "user3");
		User.login("user1", "user1");
		System.out.println(Friends.addFriend(UserTools.getKeyById(1), 2));
		System.out.println(Friends.addFriend(UserTools.getKeyById(1), 3));
		System.out.println(Friends.listFriends(UserTools.getKeyById(1)));
		Friends.deleteFriend(UserTools.getKeyById(1), 2);
		System.out.println(Friends.listFriends(UserTools.getKeyById(1)));
		
	}
	
}
