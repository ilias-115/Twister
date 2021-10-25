package test;

import java.sql.SQLException;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;

import services.Messages;
import tools.MessagesTools;
import tools.UserTools;

/**
 * @author 3670610
 *
 */
public class TestMessages {
	public static void main(String [] args) throws JSONException, SQLException {
		/*
		MongoCollection<Document> c=MessagesTools.getCollection();
		c.drop();
		System.out.println(Messages.addMessage(UserTools.getKeyById(1), 2 , "message test1"));
		System.out.println(Messages.addMessage(UserTools.getKeyById(1), 2 , "message test2"));
		System.out.println(Messages.addMessage(UserTools.getKeyById(1), 2 , "message test3"));
		Messages.deleteMessage(1);
		System.out.println(Messages.getMessages(UserTools.getKeyById(1), 2));
		System.out.println(MessagesTools.incremente());
		*/
		MessagesTools.postMessage("3buNKL8ga0amWvQ3e2W99gu1NuQeFe4W", "message Commun");
		System.out.println(MessagesTools.listMessages("XDB5s4swnSApifCOTYpLbYm30iyDNcrT"));
		MessagesTools.postComment("XDB5s4swnSApifCOTYpLbYm30iyDNcrT", 10, "commentaire numero1");
		System.out.println(MessagesTools.listMessages("XDB5s4swnSApifCOTYpLbYm30iyDNcrT"));
	}
}
