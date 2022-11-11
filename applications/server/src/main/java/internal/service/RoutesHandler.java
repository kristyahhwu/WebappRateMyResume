package internal.service;

import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import internal.dataAccess.DAO.User;
import internal.dataAccess.DTO.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import spark.Route;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static internal.service.Utilities.*;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class RoutesHandler {


    Gson gson = new Gson();

    MongoClientURI uri = new MongoClientURI("mongodb://admin:password@localhost:27017/");
    // admin username and password is not setup correctly. Logging in without it
//    MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/");//auser:apassword@
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


//        List<Document> posts = new ArrayList<>();
//        try {
//            List<Document> doc = this.postsCollection.find().into(new ArrayList<>());
//
//            for (Document d : doc) {
//                posts.add(Document.parse(d.toJson()));// what's the point of this?? Converting a Document into a Document?
//            }
//        } catch(Exception ex){
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }
//
//        return gson.toJson(posts);
    };

    public spark.Route handleSearch = (request, response) -> {
        String searchKeyword = request.queryParams("keyword");
        return search(searchKeyword);
//        System.out.println("path: /post/search, keyword:" + request.queryParams("keyword"));
//        Gson gson = new Gson();
//
//
//        Document regQuery = new Document()
//                .append("$regex", searchKeyword)
//                .append("$options", "i");
//        Document findQuery = new Document().append("title", regQuery);
//        ArrayList<Document> results = null;
//        try {
//            results = postsCollection.find(findQuery).into(new ArrayList<>());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        for(int i =0; i < results.size(); i++){
//            System.out.println(results.get(i).toJson());
//        }
//
//        if(results != null)
//        {// find record where role is x
//            System.out.println("post found");
//            return gson.toJson(results);
//        }else
//        {
//            // can't find member
//            return "Post not found";
//        }
    };


    public spark.Route handleInitDemo = (request, response) -> {
        System.out.println("path: /demo/init");
        return initializeDemo();

//        List<List<String>> posts = new ArrayList<>();
//        posts.add(List.of(LocalDateTime.now().toString(), "fresh grad looking for FTE roles", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
//        posts.add(List.of(LocalDateTime.now().toString(), "sophomore resume for first internship", "ea molestias quasi exercitationem repellat qui ipsa sit aut"));
//        posts.add(List.of(LocalDateTime.now().toString(), "some other title", "eum et est occaecati"));
//        posts.add(List.of(LocalDateTime.now().toString(),"john","desc1"));
//        posts.add(List.of(LocalDateTime.now().toString(),"John","desc2"));
//        posts.add(List.of(LocalDateTime.now().toString(),"jo","desc3"));
//        posts.add(List.of(LocalDateTime.now().toString(),"johnathan","desc4"));
//        posts.add(List.of(LocalDateTime.now().toString(),"BOJOJO","desc5"));
//
//
//        for (int i = 0; i < posts.size(); i++) {
//            System.out.printf("Inserting " + posts.get(i).get(1) + "\n");
//            try {
//                Document doc = new Document("postDate", posts.get(i).get(0))
//                        .append("title", posts.get(i).get(1))
//                        .append("description", posts.get(i).get(2));
//                // insert document into collection
//                postsCollection.insertOne(doc);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return "Initialized demo posts";
    };

    public spark.Route handleCreatePost = ((request, response) -> {
        String body = request.body();
        return createPost(body);

//        PostDTO newPost = gson.fromJson(body, PostDTO.class);
//        System.out.println("body received"+ body);
//        ObjectId resumeId = Utilities.upload(newPost.filePath, newPost.fileName, db);
//        System.out.println("Resume saved in DB with ID: "+ resumeId);
//        // insert into transactions collection
//        try {
//            Document doc = new Document("postId", UUID.randomUUID().toString())
//                    .append("postDate", LocalDateTime.now())
//                    .append("postAuthor", newPost.postAuthor)
//                    .append("title", newPost.title)
//                    .append("description", newPost.description)
//                    .append("resume", resumeId);
//            // insert document into collection
//            postsCollection.insertOne(doc);
//        }
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//        return "Done";
    });

    public spark.Route handleCreateUser = ((request, response) -> {
        String body = request.body();
        System.out.println("Creating user:\n" + body.toString());

        return createUser(body);

//        UserDTO newUser = new UserDTO();
//        try {
//            newUser = gson.fromJson(body, UserDTO.class);// converting the JSON String into a Java object (our DTO)
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        // insert into user collection
//        Document doc = new Document("userId", newUser.userId)// should be auto generated by mongodb
//                .append("username", newUser.username)
//                .append("creationDate", LocalDateTime.now())
//                .append("email", newUser.email);
//        // insert document into collection
//        applicantsCollection.insertOne(doc);
//        return "User created";
    });

    public spark.Route handleViewPost = ((request, response) -> {
        String postid = request.queryParams("postid");
        return viewPost(postid);
//        Gson gson = new Gson();
//
//        System.out.println("In handle view post: post id is " + postid);
//        Document result = null;
//        try {
//            // result = postsCollection.find(findQuery).into(new ArrayList<>());
//            result = postsCollection.find(eq("postId", postid)).first();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        if(result != null)
//        {// find record where role is x
//            System.out.println("post found");
//            // return the first match in the arrayList
//            return gson.toJson(result);//hard code now, we will find another solution later
//        }else
//        {
//            return "Post not found";
//        }
    });

    public spark.Route handleLike = ((request, response) -> {
        String body = request.body();
        return like(body);
//        System.out.println("/post/like" + body.toString());
//
//        HandleLikeDTO newLike = new HandleLikeDTO();
//        try {
//            newLike = gson.fromJson(body, HandleLikeDTO.class);// converting the JSON String into a Java object (our DTO
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        //will have postid, userid in the request body
//
//        // validate the userid
//        Document regQuery1 = new Document()
//                .append("$regex", newLike.userid)
//                .append("$options", "i");
//        Document findQuery = new Document().append("userId", regQuery1);
//        ArrayList<Document> results = null;
//        try {
//            results = applicantsCollection.find(findQuery).into(new ArrayList<>());
//            System.out.println("User found: " + results.size());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return "Error fetching User";
//        }
//
//        if(results.size() == 0){
//            return "Invalid User ID";
//        }
//
//        // validate the postid
//        Document regQuery2 = new Document()
//                .append("$regex", newLike.postid)
//                .append("$options", "i");
//        Document findQuery2 = new Document().append("postId", regQuery2);
//        ArrayList<Document> results2 = null;
//        try {
//            results2 = postsCollection.find(findQuery2).into(new ArrayList<>());
//            //System.out.println("post found: " + results2.size());
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return "Error fetching Post";
//        }
//
//        if(results2.size() == 0){
//            return "Invalid Post ID";
//        }
//        // we should check if there is already a like for this post by this same user, if there is,
//        // her should not be allowed to like again--------
//
//        // now that both values have been validated,
//        // verify if user has already liked the post
//        Document likeResult = null;
//        try {
//            // result = postsCollection.find(findQuery).into(new ArrayList<>());
//
//            likeResult = postLikesCollection.find(and(eq("userid", newLike.userid), eq("postid", newLike.postid)))
//                    .first();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        if(likeResult != null){
//            return "Post already liked";
//        }
//        // insert into postLike collection
//        Document doc = new Document("id", UUID.randomUUID().toString())// should be auto generated by mongodb
//                .append("postid", newLike.postid)
//                .append("userid", newLike.userid);
//        // insert document into collection
//        postLikesCollection.insertOne(doc);
//        return "Post likes updated";
    });

    public spark.Route handleComment = ((request, response) -> {
        // will have postid, user (the whole class object, not just id),
        // comment, time as request body.
        String body = request.body();
        return comment(body);
//        HandleCommentDTO newComment = new HandleCommentDTO();
//        try {
//            newComment = gson.fromJson(body, HandleCommentDTO.class);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        // validate the post is correct
//        Document result = null;
//        try {
//            result = postsCollection.find(eq("postId", newComment.postid)).first();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return "Error fetching Post: " + e.getMessage();
//        }
//        if(result == null)
//        {
//            return "Post not found";
//        }
//
//        // validate the user is correct
//        Document result2 = null;
//        try {
//            result = applicantsCollection.find(eq("userId", newComment.userid)).first();
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            return "Error fetching User";
//        }
//
//        if(result == null){
//            return "Invalid User ID";
//        }
//        // now that both values have been validated,
//        // insert into postComment collection
//        LocalDateTime commentTime = Utilities.extractTime(newComment.time);
//        Document doc = new Document("id", UUID.randomUUID().toString())// should be auto generated by mongodb
//                .append("postid", newComment.postid)
//                .append("userid", newComment.userid)
//                .append("comment", newComment.comment)
//                .append("time", commentTime);
//
//        // insert document into collection
//        postCommentsCollection.insertOne(doc);
//        return "Post comment added";

    });

    //user login endpoint
    public spark.Route handleLogin = ((request, response) -> {
        String body = request.body();
        return login(body);
//        UserLoginDTO loginDto = gson.fromJson(body, UserLoginDTO.class);
//        System.out.println("body received"+ body);
//        Document search = applicantsCollection.find(eq("username", loginDto.username)).first();
//        if (search != null) {// find record where username is x
//            System.out.println("user found");
//            if (search.get("password").equals(loginDto.password)) {
//                // if record is found, check if password is same as logindto password
//                return gson.toJson(new UserLoginResponseDTO(true, null));
//            } else {
//                return gson.toJson(new UserLoginResponseDTO(false, "Invalid password"));
//            }
//        } else {
//            //can't find user
//            return gson.toJson(new UserLoginResponseDTO(false, "User not found"));
//        }
    });

    // logout endpoint
    public spark.Route handleLogout = ((request, response) -> {
        return logout();
//        // remove session from user if exist
//        return gson.toJson(new UserLoginResponseDTO(false, "User logged out"));
    });
    //GetNumberOfLikesForPost()
    public spark.Route handleNumberOfLikes = ((request, response) -> {
        String postId = request.queryParams("postid");
        return numberOfLikes(postId);
//        List<Document> likeResult = null;
//        try {
//
//            likeResult = postLikesCollection.find(eq("postid", postId)).into(new ArrayList<>());
//            return "Number of likes: " + likeResult.size();
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return "Number of likes: " + (likeResult == null ? 0 : likeResult.size());
    });

    //GetCommentsForPost()
    public spark.Route handleGetCommentsForPost = ((request, response) -> {
        String postId = request.queryParams("postid");
        return getCommentsForPost(postId);
//        List<Document> comments = null;
//        try {
//
//            comments = postCommentsCollection.find(eq("postid", postId)).into(new ArrayList<>());
//
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//        return gson.toJson(comments);
    });

}
