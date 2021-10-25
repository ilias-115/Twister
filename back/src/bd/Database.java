package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.bson.Document;

import com.mongodb.client.*;

public class Database {
	private DataSource dataSource;
	private static Database database;
	
	public Database(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env" + jndiname);
		} catch (NamingException e) {
			throw new SQLException(jndiname + " is missing JNDI! : "+e.getMessage());
		}
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static Connection getMySQLConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (DBStatic.mysql_pooling == false) {
			return (DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/"
					+DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password));
		} else {
			if (database == null) {
				database = new Database("jdbc/db");
			}
			return (database.getConnection());
		}	
	}
	
	public static MongoDatabase mongoCreate() throws SQLException{
		MongoClient mongo = MongoClients.create(DBStatic.mongo_create);
		MongoDatabase db = mongo.getDatabase(DBStatic.mongo_db);
		return db;
	}
	
	public static MongoCollection<Document> mongoCollection(String nom) throws SQLException{
		return mongoCreate().getCollection(nom);
	}

}	