package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {
	
	public static ArrayList<String> get() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM wordOccurrences.word");

			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()) {
				System.out.println(result.getString("word"));
				
				array.add(result.getString("word"));
			}
			System.out.println("All record added");
			return array;
		} catch(Exception E) {
			System.out.println(E);
		}
		
		return null;	
	}
	
	public static void post(String word, String occurrences) throws Exception {
		try {
		Connection con = getConnection();
		PreparedStatement posted = con.prepareStatement("INSERT INTO word (word, occurrences) VALUES ('"+word+"', '"+occurrences+"')");
		posted.executeUpdate();
		} catch(Exception ex) {System.out.println(ex);}
		finally {
			System.out.println("Insert completed");
		}
	}
	
	public static void postNewWords(String newWord) throws Exception {
		
		
		try {
		Connection con = getConnection();
		PreparedStatement posted = con.prepareStatement("INSERT INTO word (word, occurrences) VALUES ('"+newWord+"', '"+1+"')");
		posted.executeUpdate();
		} catch(Exception ex) {System.out.println(ex);}
		finally {
			System.out.println("Insert completed");
		}
	}
	
	public static void createTable() throws Exception {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS word(id int NOT NULL AUTO_INCREMENT, word varchar(255), PRIMARY KEY(id)");
			create.executeUpdate();
			
			} catch(Exception ex) {System.out.println(ex);}
		finally {
			System.out.println("Function Complete.");
		}
	}
	
	public static Connection getConnection() throws Exception {
		try {
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/WordOccurrences";
			String username = "root";
			String password = "password"; //NOTE: change to actual root password before running
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected..");
			return conn;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

}
