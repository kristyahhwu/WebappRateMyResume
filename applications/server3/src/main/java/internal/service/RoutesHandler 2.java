package internal.service;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class RoutesHandler {

    Gson gson = new Gson();

    MongoClientURI uri = new MongoClientURI("mongodb://admin:password@localhost:27017/");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase db = mongoClient.getDatabase("UsersDatabase");
    MongoCollection<Document> postsCollection = db.getCollection("postsCollection");

    // Quick sanity check that backend is running properly
    public spark.Route handleHome = (request, response) -> "Backend is running!";

    // set CORS policy during preflight check
    public spark.Route handlePreflight = (request, response) -> {
        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
        if (accessControlRequestHeaders != null) {
            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
        }

        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
        if (accessControlRequestMethod != null) {
            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
        }
        return "OK";
    };

    public spark.Route handleGetAll = (request, response) -> {
        System.out.println("path: /post/getAll");

        List<Document> posts = new ArrayList<>();
        List<Document> doc = this.postsCollection.find().into(new ArrayList<>());

        for (Document d : doc) {
            posts.add(Document.parse(d.toJson()));
        }
        return gson.toJson(posts);
    };

    public spark.Route handleSearch = (request, response) -> {
        String searchKeyword = request.queryParams("keyword");
        System.out.println("path: /post/search, keyword:" + request.queryParams("keyword"));

        Gson gson = new Gson();

        // Modify to get partial match: https://www.mongodb.com/docs/realm/sdk/java/examples/mongodb-remote-access/
        Document search = postsCollection.find(eq("title", searchKeyword)).first();
        System.out.println("search results: " + search.toString());
        if (search != null) {// find record where role is x
            System.out.println("post found");

            return gson.toJson(Document.parse(search.toJson()));

        } else {
            //can't find member
            return "Post not found";
        }
    };

    public spark.Route handleInitDemo = (request, response) -> {
        System.out.println("path: /demo/init");

        List<List<String>> posts = new ArrayList<>();
        posts.add(List.of(LocalDateTime.now().toString(), "fresh grad looking for FTE roles", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        posts.add(List.of(LocalDateTime.now().toString(), "sophomore resume for first internship", "ea molestias quasi exercitationem repellat qui ipsa sit aut"));
        posts.add(List.of(LocalDateTime.now().toString(), "some other title", "eum et est occaecati"));

        for (int i = 0; i < posts.size(); i++) {
            System.out.printf("Inserting " + posts.get(i).get(1) + "\n");
            try {
                Document doc = new Document("postDate", posts.get(i).get(0))
                        .append("title", posts.get(i).get(1))
                        .append("description", posts.get(i).get(2));
                // insert document into collection
                postsCollection.insertOne(doc);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "Initialized demo posts";
    };
}
