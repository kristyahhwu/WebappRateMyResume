package internal.service;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import internal.dataAccess.DAO.User;
import internal.dataAccess.DTO.UserDTO;
import internal.dataAccess.DTO.PostDTO;
import org.bson.Document;
import org.bson.types.ObjectId;

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
    MongoCollection<Document> applicantsCollection = db.getCollection("applicantsCollection");

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
            posts.add(Document.parse(d.toJson()));// what's the point of this?? Converting a Document into a Document?
        }
        return gson.toJson(posts);
    };

    public spark.Route handleSearch = (request, response) -> {
        String searchKeyword = request.queryParams("keyword");
        System.out.println("path: /post/search, keyword:" + request.queryParams("keyword"));
        Gson gson = new Gson();
//        Document search = null;
        // try {
        // // Modify to get partial match:
        // https://www.mongodb.com/docs/realm/sdk/java/examples/mongodb-remote-access/
        // search = postsCollection.find(eq("title", searchKeyword)).first();
        // }catch(Exception e){
        // e.printStackTrace();
        // }

        Document regQuery = new Document()
                .append("$regex", searchKeyword)
                .append("$options", "i");
        Document findQuery = new Document().append("title", regQuery);
        ArrayList<Document> results = null;
        try {
            results = postsCollection.find(findQuery).into(new ArrayList<>());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        for(int i =0; i < results.size(); i++){
            System.out.println(results.get(i).toJson());
        }

//    System.out.println("search results: "+search.toString());if(search!=null)
//        System.out.println("search results: " + results.toString());
        if(results != null)
        {// find record where role is x
            System.out.println("post found");

//        return gson.toJson(Document.parse(search.toJson()));
            return gson.toJson(results);
        }else
        {
            // can't find member
            return "Post not found";
        }
    };


    public spark.Route handleInitDemo = (request, response) -> {
        System.out.println("path: /demo/init");

        List<List<String>> posts = new ArrayList<>();
        posts.add(List.of(LocalDateTime.now().toString(), "fresh grad looking for FTE roles", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        posts.add(List.of(LocalDateTime.now().toString(), "sophomore resume for first internship", "ea molestias quasi exercitationem repellat qui ipsa sit aut"));
        posts.add(List.of(LocalDateTime.now().toString(), "some other title", "eum et est occaecati"));
        posts.add(List.of(LocalDateTime.now().toString(),"john","desc1"));
        posts.add(List.of(LocalDateTime.now().toString(),"John","desc2"));
        posts.add(List.of(LocalDateTime.now().toString(),"jo","desc3"));
        posts.add(List.of(LocalDateTime.now().toString(),"johnathan","desc4"));
        posts.add(List.of(LocalDateTime.now().toString(),"BOJOJO","desc5"));


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

    public spark.Route handleCreatePost = ((request, response) -> {
        String body = request.body();

        PostDTO newPost = gson.fromJson(body, PostDTO.class);
        System.out.println("body received"+ body);
        ObjectId resumeId = Utilities.upload(newPost.filePath, newPost.fileName, db);
        System.out.println("Resume saved in DB with ID: "+ resumeId);
        // insert into transactions collection
        try {
            Document doc = new Document("postId", newPost.postId)
                    .append("postDate", LocalDateTime.now())
                    .append("postAuthor", newPost.postAuthor)
                    .append("resume", resumeId);
            // insert document into collection
            postsCollection.insertOne(doc);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return "Done";
    });

    public spark.Route handleCreateUser = ((request, response) -> {
        String body = request.body();
        UserDTO newUser = new UserDTO();
        try {
            newUser = gson.fromJson(body, UserDTO.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        // insert into applicants collection
        Document doc = new Document("userId", newUser.userId)// should be auto generated by mongo db
                .append("username", newUser.username)
                .append("creationDate", LocalDateTime.now())
                .append("email", newUser.email);
        // insert document into collection
        applicantsCollection.insertOne(doc);
        return "User created";
    });
}
