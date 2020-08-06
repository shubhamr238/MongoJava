import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;

import java.util.Iterator;

public class MongoJava {

	public static void main(String[] args) {
		
		try {
			// Creating a Mongo client 
		      MongoClient mongouri = new MongoClient( "localhost" , 27017 ); 
		   
		      // Creating Credentials 
		      MongoCredential credential; 
		      credential = MongoCredential.createCredential("sampleUser", "mongoJavaTestDB", 
		         "password".toCharArray()); 
		      System.out.println("Connected to the database successfully");  
		      
		      // Accessing the database 
		      MongoDatabase database = mongouri.getDatabase("mongoJavaTestDB"); 
		      System.out.println("Credentials ::"+ credential);     
		      
		   // Creating a collection
		  	database.createCollection("sampleCollection");
		  	System.out.println("Collection created successfully");
		  	
		  	// Retrieving a collection
		  	MongoCollection<Document> collection = database.getCollection("sampleCollection");
		  	System.out.println("Collection sampleCollection selected successfully");
		  	Document document = new Document("title", "MongoDB")
		  	.append("description", "database")
		  	.append("likes", 100)
		  	.append("by", "shubhamr238");
		  	
		  	//Inserting document into the collection
		  	collection.insertOne(document);
		  	System.out.println("Document inserted successfully");
			/*
			 * //It can also be done like this if many docs needs to be inserted
			 * List<Document> list = new ArrayList<Document>(); list.add(document1);
			 * list.add(document2); collection.insertMany(list);
			 */
		  	
		  	// Getting the iterable object
			FindIterable<Document> iterDoc = collection.find();
			int i = 0;
			// Getting the iterator
			Iterator<Document> it = iterDoc.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
				i++;
			}
			System.out.println("Total Number of Record:"+i);
			
			//closing the connection
			mongouri.close();
			
		}
		catch(Exception e) {
			System.out.println("Error!");
			System.out.println(e.getMessage());
		}

	}

}
