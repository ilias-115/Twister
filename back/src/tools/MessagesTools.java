package tools;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import bd.Database;

public class MessagesTools {
	
	public static int incremente() throws SQLException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		MongoCursor<Document> cursor=c.find().iterator();
		int max=0;
		while(cursor.hasNext()) {
			Document o=cursor.next();
			if(o.getInteger("idMessage")>max) {
				max=o.getInteger("idMessage");
			}
		}
		return max+1;
		
	}
	/*
	public static ArrayList<String> listMessages(String key, int idFriend) throws SQLException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		Document d= new Document();
		ArrayList<String> res = new ArrayList<>();
		d.append("idSender", UserTools.getIdUserByKey(key));
		d.append("idReceiver", idFriend);
		
		MongoCursor<Document> cursor=c.find(d).iterator();
		while(cursor.hasNext()) {
			Document o=cursor.next();
			res.add(o.getString("message"));
		}
		
		return res; 
	}
	*/
	public static JSONObject listMessages(String key, int idFriend) throws SQLException, JSONException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		Document d= new Document();
		JSONObject j;
		JSONArray array = new JSONArray();
		d.append("idSender", UserTools.getIdUserByKey(key));
		d.append("idReceiver", idFriend);
		
		MongoCursor<Document> cursor=c.find(d).iterator();
		while(cursor.hasNext()) {
			Document o=cursor.next();
			j = new JSONObject();
			j.put("message", o.getString("message")+" idMessage : "+o.getInteger("idMessage")+", de : "+UserTools.getPrenomByIdUser(o.getInteger("idSender"))+", le : "+o.getDate("Date"));
			array.put(j);
		}
		JSONObject ret = new JSONObject();
		ret.put("messages", array);
		return ret;
	}
	
	public static JSONObject listMessages(String key) throws SQLException, JSONException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		Document d= new Document();
		JSONObject j;
		JSONArray array = new JSONArray();
		d.append("idReceiver", -1);
		
		MongoCursor<Document> cursor=c.find(d).iterator();
		while(cursor.hasNext()) {
			Document o=cursor.next();
			j = new JSONObject();
			j.put("message", o.getString("message")+" idMessage : "+o.getInteger("idMessage")+", de : "+UserTools.getPrenomByIdUser(o.getInteger("idSender"))+", le : "+o.getDate("Date")+" commentaires : "+o.get("commentaires"));
			array.put(j);
		}
		JSONObject ret = new JSONObject();
		ret.put("messages", array);
		return ret;
	}
	public static ArrayList<Integer> listIdMessages() throws SQLException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		ArrayList<Integer> res = new ArrayList<>();
		
		MongoCursor<Document> cursor=c.find().iterator();
		while(cursor.hasNext()) {
			Document o=cursor.next();
			res.add(o.getInteger("idMessage"));
		}
		
		return res; 
	}
	
	public static void sendMessage(String key, int idFriend, String message) throws SQLException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		Document d= new Document();
		GregorianCalendar calendar =new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		d.append("message", message);
		d.append("idSender", UserTools.getIdUserByKey(key));
		d.append("idReceiver", idFriend);
		d.append("idMessage", incremente());
		d.append("Date",date);
		c.insertOne(d);
	}
	
	public static void postMessage(String key, String message) throws SQLException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		Document d= new Document();
		GregorianCalendar calendar =new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		d.append("message", message);
		d.append("idSender", UserTools.getIdUserByKey(key));
		d.append("idReceiver", -1);
		d.append("idMessage", incremente());
		d.append("commentaires", new ArrayList<Document>());
		d.append("Date",date);
		c.insertOne(d);
	}
	public static void deleteMessage(int idMessage) throws SQLException{
		MongoCollection<Document> c;
		c = Database.mongoCollection("messages");
		Document d= new Document();
		d.append("idMessage",idMessage);
		c.deleteOne(d);
	}
	
	public static MongoCollection<Document> getCollection() throws SQLException{
		return Database.mongoCollection("messages");
	}
	
	public static void postComment(String key,int idMessage, String commentaire) throws SQLException{
		MongoCollection<Document> c=Database.mongoCollection("messages");
		Document d= new Document();
		GregorianCalendar calendar =new java.util.GregorianCalendar();
		Date date = calendar.getTime();
		d.put("idSender", UserTools.getIdUserByKey(key));
		d.put("idMessage",idMessage);
		d.put("commentaire", commentaire);
		d.put("date",date);
		
		Document query = new Document();
		query.put("idMessage", idMessage);
		MongoCursor<Document> curs = c.find(query).iterator();
		Document o = new Document();
		if(curs.hasNext()) {
			o =  curs.next();
		}
		ArrayList<Document> liste_commentaires = (ArrayList<Document>) o.get("commentaires");
		int id = liste_commentaires.size();
		d.put("idSender", id);
		liste_commentaires.add(d);
		Document replace = new Document();
		replace.append("$set", new Document().append("commentaires", liste_commentaires));
		c.updateOne(query, replace);
	}
	
	
	public static ArrayList<String> searchMessages(String key, String message){
		return null;
	}
}
