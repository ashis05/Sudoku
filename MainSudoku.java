import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import javax.swing.JOptionPane;

import org.bson.Document;

public class MainSudoku {
	int Level ;
	String Username, Password;
    public void create_profile(String username,String pass)
	{String uri = "mongodb+srv://wadhwaniashis:Ashiswadh05@sudoku.xounz3u.mongodb.net/?retryWrites=true&w=majority";

    try (MongoClient mongoClient = MongoClients.create(uri)) {
        MongoDatabase database = mongoClient.getDatabase("db");
        MongoCollection<Document> collection = database.getCollection("Sudoku");

        Document doc = new Document("Username",username)
        		.append("Password",pass)
        		.append("Level", 1);
        collection.insertOne(doc);}}
    
    
    public boolean check_credentials(String username, String pass) {
        String uri = "mongodb+srv://wadhwaniashis:Ashiswadh05@sudoku.xounz3u.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("db");
            MongoCollection<Document> collection = database.getCollection("Sudoku");

            Document query = new Document("Username", username);
            Document result = collection.find(query).first();

            if (result != null && result.getString("Password").equals(pass)) {
            	return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions related to database operations
            return false;
        }
    }
    
    public int get_level(String username, String pass)
    {
    	 String uri = "mongodb+srv://wadhwaniashis:Ashiswadh05@sudoku.xounz3u.mongodb.net/?retryWrites=true&w=majority";

         try (MongoClient mongoClient = MongoClients.create(uri)) {
             MongoDatabase database = mongoClient.getDatabase("db");
             MongoCollection<Document> collection = database.getCollection("Sudoku");

             Document query = new Document("Username", username);
             Document result = collection.find(query).first();
        if (result.getString("Password").equals(pass))   		 
    	{int level = result.getInteger("Level");
    	Username = username;
        Password = pass;
    	Level = level;
        return level;
    	}
        else
        {
        	return 0;
        }
    }}
    public int Level()
    {
    	return Level;
    }
    public void Update_Level(int count,String username,String pass)
    {
    	String uri = "mongodb+srv://wadhwaniashis:Ashiswadh05@sudoku.xounz3u.mongodb.net/?retryWrites=true&w=majority";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("db");
            MongoCollection<Document> collection = database.getCollection("Sudoku");
            Document query = new Document("Username", username).append("Password", pass);
            Document result = collection.find(query).first();
            
            // Check if result is not null
            if (result != null) {
                // Update operation
                Document update = new Document("$set", new Document("Level", count));
                // Apply the update operation using updateOne()
                collection.updateOne(query, update);
            } else {
                System.out.println("No document found for the provided username and password combination.");
                System.out.println("Username:"+ username);
                System.out.println("Passowrd:"+ pass);
                System.out.println("Level:"+ count);
            }
            
    	
    }
}
    }
