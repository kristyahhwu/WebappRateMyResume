import static internal.service.RoutesHandler.*;
import static spark.Spark.*;
import static spark.Spark.before;


import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import internal.dataAccess.DTO.UserDTO;
import internal.dataAccess.DTO.PostDTO;
import internal.service.RoutesHandler;
import internal.service.Utilities;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

public class SparkServer {

    public static void main(String[] args) {
        RoutesHandler handler = new RoutesHandler();

        port(4321);

//        // open connection
//        MongoClientURI uri = new MongoClientURI("mongodb://admin:password@localhost:27017/");
//        MongoClient mongoClient = new MongoClient(uri);
//        // get ref to database
//        MongoDatabase db = mongoClient.getDatabase("UsersDatabase");
//        // get ref to collection
//        MongoCollection<Document> teamMemberCollection = db.getCollection("teamMemberCollection");
//        // get ref to users collection
//        //db.createCollection("applicantsCollection");
//        MongoCollection<Document> applicantsCollection = db.getCollection("applicantsCollection");
//
//        // get ref to post collections
//        //db.createCollection("postsCollection");
//        MongoCollection<Document> postsCollection = db.getCollection("postsCollection");
//        // get ref to resume collection
//        //db.createCollection("resumeCollection");
//        MongoCollection<Document> resumeCollection = db.getCollection("resumeCollection");
//
//        Gson gson = new Gson();

        //Set CORS policy
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        options("/*", handler.handlePreflight);

        get("/", handler.handleHome);

        get("/post/getAll", handler.handleGetAll);
        get("/post/search", handler.handleSearch);// returns a collections of Posts, uses keyword
        post("/post/create", handler.handleCreatePost);

        get("/post/view", handler.handleViewPost); // will have postid in the query parameter.
                                                        // See handleSearch for example, returns a single Post
        put("/post/view/like", handler.handleLike); // will have postid, userid in the request body
                                                        // (not query parameters).
        put("/post/comment", handler.handleComment); // will have postid, user (the whole class object, not just id),
                                                            // comment, time as request body.

        post("/user/login", handler.handleLogin); // Do this last. Might not need this at all
        post("/user/logout", handler.handleLogout); // Do this last. Might not need this at all
        post("/user/create/", handler.handleCreateUser);

        get("/demo/init", handler.handleInitDemo);
        //--------------------------------------------------------------------------------------------------------------



    }


}


