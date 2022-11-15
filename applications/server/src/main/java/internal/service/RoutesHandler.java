package internal.service;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static internal.service.Utilities.*;


public class RoutesHandler {


    Gson gson = new Gson();

    MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/");
    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase db = mongoClient.getDatabase("UsersDatabase");

    MongoCollection<Document> postsCollection = db.getCollection("postsCollection");
    MongoCollection<Document> applicantsCollection = db.getCollection("applicantsCollection");
    MongoCollection<Document> postLikesCollection = db.getCollection("postLikesCollection");
    MongoCollection<Document> postCommentsCollection = db.getCollection("postCommentsCollection");

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
        return getAll();

    };

    public spark.Route handleSearch = (request, response) -> {
        String searchKeyword = request.queryParams("keyword");
        return search(searchKeyword);
    };


    public spark.Route handleInitDemo = (request, response) -> {
        System.out.println("path: /demo/init");
        return initializeDemo();
    };

    public spark.Route handleCreatePost = ((request, response) -> {
        String body = request.body();
        return createPost(body);
    });

    public spark.Route handleCreateUser = ((request, response) -> {
        String body = request.body();
        System.out.println("Creating user:\n" + body.toString());

        return createUser(body);
    });

    public spark.Route handleViewPost = ((request, response) -> {
        String postid = request.queryParams("postid");
        return viewPost(postid);
    });

    public spark.Route handleLike = ((request, response) -> {
        String body = request.body();
        return like(body);
    });

    public spark.Route handleComment = ((request, response) -> {
        // will have postid, user (the whole class object, not just id),
        // comment, time as request body.
        String body = request.body();
        return comment(body);
    });

    //user login endpoint
    public spark.Route handleLogin = ((request, response) -> {
        String body = request.body();
        return login(body);
    });

    // logout endpoint
    public spark.Route handleLogout = ((request, response) -> {
        return logout();
    });

    //GetNumberOfLikesForPost()
    public spark.Route handleNumberOfLikes = ((request, response) -> {
        String postId = request.queryParams("postid");
        return numberOfLikes(postId);
    });

    //GetCommentsForPost()
    public spark.Route handleGetCommentsForPost = ((request, response) -> {
        String postId = request.queryParams("postid");
        return getCommentsForPost(postId);
    });

}
