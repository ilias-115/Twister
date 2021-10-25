package test;
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
import bd.Database;

public class testttt {

	public static void main(String [] args) throws SQLException {
		
		MongoCollection<Document> c = Database.mongoCollection("lol");
		Document d1 = new Document();
		Document d2 = new Document();

		d1.append("id", 0);
		d2.append("id", 1);
		d1.append("message", "loool1");
		d2.append("message", "loool2");
		ArrayList<Document> liste = new ArrayList<Document>();
		liste.add(d1);
		liste.add(d2);
		Document query = new Document();
		query.append("$in", liste);
		c.insertOne(query);
		
		MongoCursor curs = c.find().iterator();
		while(curs.hasNext()) {
			System.out.println(curs.next());
		}
		
	}




}
