package internal.service;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.result.DeleteResult;
import internal.dataAccess.DTO.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.io.*;
import java.time.LocalDateTime;
import java.awt.image.BufferedImage;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class Utilities {

    static Gson gson = new Gson();

    // admin username and password is not setup correctly. Logging in without it
    static MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017/");
    static MongoClient mongoClient = new MongoClient(uri);
    static MongoDatabase db = mongoClient.getDatabase("UsersDatabase");

    static MongoCollection<Document> postsCollection = db.getCollection("postsCollection");
    static MongoCollection<Document> applicantsCollection = db.getCollection("applicantsCollection");
    static MongoCollection<Document> postLikesCollection = db.getCollection("postLikesCollection");
    static MongoCollection<Document> postCommentsCollection = db.getCollection("postCommentsCollection");

    public static String createUser(String body){
        UserDTO newUser = new UserDTO();
        try {
            newUser = gson.fromJson(body, UserDTO.class);// converting the JSON String into a Java object (our DTO)
            newUser.userId = UUID.randomUUID().toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        // insert into user collection
        Document doc = new Document("userId", newUser.userId)
                .append("username", newUser.username)
                .append("password", newUser.password)
                .append("creationDate", LocalDateTime.now())
                .append("email", newUser.email);
        // insert document into collection
        applicantsCollection.insertOne(doc);
        return newUser.userId;
    }

    public static byte[] LoadImage(String filePath) throws Exception {
        File file = new File(filePath);
        int size = (int) file.length();
        byte[] buffer = new byte[size];
        FileInputStream in = new FileInputStream(file);
        in.read(buffer);
        in.close();
        return buffer;
    }

    public static ObjectId upload(String filePath, String fileName, MongoDatabase imgDb) {
        ObjectId fileId = null;
        try {
            // Create a gridFSBucket
            GridFSBucket gridBucket = GridFSBuckets.create(imgDb);


            InputStream inStream = new FileInputStream(new File(filePath + "/" + fileName));

            // Create some customize options for the details that describes
            // the uploaded image
            GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024).metadata(new Document("type", "image").append("content_type", "image/png"));

            fileId = gridBucket.uploadFromStream(fileName, inStream, uploadOptions);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileId;
    }

    public static LocalDateTime extractTime(String time) {
        // expect the time format to be year-month-date-hour-minute
        String[] parts = time.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        int hour = Integer.parseInt(parts[3]);
        int minute = Integer.parseInt(parts[4]);

        LocalDateTime currentTime = LocalDateTime.of(year, month, day, hour, minute);
        return currentTime;
    }

    public static byte[] convertPDFToByteArray(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] data = new byte[(int) file.length()];
        try {
            int readNum = fis.read(data);
            while (readNum != -1) {
                bos.write(data, 0, readNum);
                readNum = fis.read(data);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit((1));
        }
        data = bos.toByteArray();
        return data;
    }

    public static void convertByteArrayToImage(byte[] data, String filename) throws IOException {
        PDDocument document = PDDocument.load(data);
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(i, 300, ImageType.RGB);
            ImageIOUtil.writeImage(bim, String.format("%s-%d.%s", filename, i + 1, "jpg"), 300);
        }
        document.close();
    }

    public static void generateImages(String path) {
        byte[] byteArray = null;
        String filename = new File(path).getName();
        try {
            byteArray = convertPDFToByteArray(path);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            convertByteArrayToImage(byteArray, filename);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String createPost(String body) {
        PostDTO newPost = gson.fromJson(body, PostDTO.class);
        System.out.println("body received"+ body);

        String newPostID = UUID.randomUUID().toString();
        // insert into transactions collection
        try {
            Document doc = new Document("postId", newPostID)
                    .append("postDate", LocalDateTime.now())
                    .append("postAuthor", newPost.author)
                    .append("title", newPost.title)
                    .append("description", newPost.description)
                    .append("resumeUrl", newPost.resumeUrl);
            // insert document into collection
            postsCollection.insertOne(doc);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return newPostID;
    }

    public static String initializeDemo() {
        List<List<String>> posts = new ArrayList<>();
        posts.add(List.of(LocalDateTime.now().toString(), "fresh grad looking for FTE roles",
                "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
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
    }

    public static String search(String searchKeyword) {
        Gson gson = new Gson();


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

        if(results != null)
        {// find record where role is x
            System.out.println("post found");
            return gson.toJson(results);
        }else
        {
            // can't find member
            return "Post not found";
        }
    }

    public static String getAll() {
        List<Document> posts = new ArrayList<>();
        try {
            List<Document> doc = postsCollection.find().into(new ArrayList<>());

            for (Document d : doc) {
                posts.add(Document.parse(d.toJson()));// what's the point of this?? Converting a Document into a Document?
            }
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return gson.toJson(posts);
    }

    public static String viewPost(String postid) {
        Gson gson = new Gson();

        System.out.println("In handle view post: post id is " + postid);
        Document result = null;
        try {
            // result = postsCollection.find(findQuery).into(new ArrayList<>());
            result = postsCollection.find(eq("postId", postid)).first();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(result != null)
        {// find record where role is x
            System.out.println("post found");
            // return the first match in the arrayList
            return gson.toJson(result);//hard code now, we will find another solution later
        }else
        {
            return "Post not found";
        }
    }

    public static String like(String body) {
        System.out.println("/post/like" + body.toString());

        HandleLikeDTO newLike = new HandleLikeDTO();
        try {
            newLike = gson.fromJson(body, HandleLikeDTO.class);// converting the JSON String into a Java object (our DTO
        }catch(Exception e){
            e.printStackTrace();
        }
        //will have postid, userid in the request body

        // validate the userid
        Document regQuery1 = new Document()
                .append("$regex", newLike.userid)
                .append("$options", "i");
        Document findQuery = new Document().append("userId", regQuery1);
        ArrayList<Document> results = null;
        try {
            results = applicantsCollection.find(findQuery).into(new ArrayList<>());
            System.out.println("User found: " + results.size());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error fetching User";
        }

        if(results.size() == 0){
            return "Error: Invalid User ID";
        }

        // validate the postid
        Document regQuery2 = new Document()
                .append("$regex", newLike.postid)
                .append("$options", "i");
        Document findQuery2 = new Document().append("postId", regQuery2);
        ArrayList<Document> results2 = null;
        try {
            results2 = postsCollection.find(findQuery2).into(new ArrayList<>());
            //System.out.println("post found: " + results2.size());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Error fetching Post";
        }

        if(results2.size() == 0){
            return "Error: Invalid Post ID";
        }
        // we should check if there is already a like for this post by this same user, if there is,
        // her should not be allowed to like again--------

        // now that both values have been validated,
        // verify if user has already liked the post
        Document likeResult = null;
        try {
            // result = postsCollection.find(findQuery).into(new ArrayList<>());

            likeResult = postLikesCollection.find(and(eq("userid", newLike.userid), eq("postid", newLike.postid)))
                    .first();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(likeResult != null){
            return "Error: Post already liked";
        }
        // insert into postLike collection
        String newLIkeID = UUID.randomUUID().toString();
        Document doc = new Document("id", newLIkeID)// should be auto generated by mongodb
                .append("postid", newLike.postid)
                .append("userid", newLike.userid);
        // insert document into collection
        postLikesCollection.insertOne(doc);
        return newLIkeID;
    }

    public static int countNumberOfLikesForPost(String postID){
        ArrayList<Document> likeResult = null;
        try {
            // result = postsCollection.find(findQuery).into(new ArrayList<>());

            likeResult = postLikesCollection.find( eq("postid", postID)).into(new ArrayList<>());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return likeResult.size();
    }

    public static String comment(String body) {
        CommentDTO newComment = new CommentDTO();
        try {
            newComment = gson.fromJson(body, CommentDTO.class);
            // Add short timestamp to the comment
            LocalDateTime UnformattedTime = LocalDateTime.now();
            DateTimeFormatter shortTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
            newComment.time = UnformattedTime.format(shortTimeFormatter).toString();
        } catch (Exception e){
            e.printStackTrace();
        }

        // validate the post is correct
        Document result = null;
        try {
            result = postsCollection.find(eq("postId", newComment.postid)).first();
        } catch (Exception e){
            System.out.printf("Error querying post collection with postid: %s. %s", newComment.postid, e.getMessage());
            return "Error fetching Post: " + e.getMessage();
        }

        if (result == null)
        {
            System.out.printf("No post found with postid: %s", newComment.postid);
            return "Post not found";
        }

        // validate the user is correct
        Document result2 = null;
        try {
            result = applicantsCollection.find(eq("userId", newComment.userid)).first();
        }catch (Exception e){
            System.out.printf("Error fetchign user: %s", e.getMessage());
            System.out.println(e.getMessage());
            return "Error fetching User";
        }

        if(result == null){
            System.out.printf("No user found with userid: %s", newComment.userid);
            return "Invalid User ID";
        }
        // now that both values have been validated,
        // insert into postComment collection
        String newCommentID = UUID.randomUUID().toString();
        Document doc = new Document("id", newCommentID)// should be auto generated by mongodb
                .append("postid", newComment.postid)
                .append("userid", newComment.userid)
                .append("username", newComment.username)
                .append("comment", newComment.comment)
                .append("time", newComment.time);

        // insert document into collection
        postCommentsCollection.insertOne(doc);
        System.out.println("Post comment added");
        return newCommentID;

    }

    public static String login(String body) {
        UserLoginDTO loginDto = gson.fromJson(body, UserLoginDTO.class);
        System.out.println("body received"+ body);
        Document search = applicantsCollection.find(eq("username", loginDto.username)).first();
        if (search != null) {// find record where username is x
            System.out.println("user found");
            if (search.get("password").equals(loginDto.password)) {
                // if record is found, check if password is same as logindto password
                return gson.toJson(new UserLoginResponseDTO(true, null));
            } else {
                return gson.toJson(new UserLoginResponseDTO(false, "Invalid password"));
            }
        } else {
            //can't find user
            return gson.toJson(new UserLoginResponseDTO(false, "User not found"));
        }
    }

    public static String logout() {
        // remove session from user if exist
        return gson.toJson(new UserLoginResponseDTO(false, "User logged out"));
    }


    public static String numberOfLikes(String postId) {
        List<Document> likeResult = null;
        try {

            likeResult = postLikesCollection.find(eq("postid", postId)).into(new ArrayList<>());
            return "Number of likes: " + likeResult.size();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "Number of likes: " + (likeResult == null ? 0 : likeResult.size());
    }

    public static String getCommentsForPost(String postId) {

        List<Document> comments = null;
        try {

            comments = postCommentsCollection.find(eq("postid", postId)).into(new ArrayList<>());


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println(gson.toJson(comments));
        return gson.toJson(comments);
    }

    public static void deletePost(String newPostID) {
        System.out.println("Post id toDelete: " + newPostID);
        Bson query = eq("postId", newPostID);
        try {
            DeleteResult result = postsCollection.deleteOne(query);
            System.out.println("Deleted document count: " + result.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("Unable to delete due to an error: " + me);
        }
    }

    public static void deleteLike(String newLikeID) {
        System.out.println("Like id toDelete: " + newLikeID);
        Bson query = eq("id", newLikeID);
        try {
            DeleteResult result = postLikesCollection.deleteOne(query);
            System.out.println("Deleted document count: " + result.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("Unable to delete due to an error: " + me);
        }
    }

    public static void deleteComment(String newCommentID) {
        System.out.println("Comment id toDelete: " + newCommentID);
        Bson query = eq("id", newCommentID);
        try {
            DeleteResult result = postCommentsCollection.deleteOne(query);
            System.out.println("Deleted document count: " + result.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("Unable to delete due to an error: " + me);
        }
    }

    public static void deleteUser(String userId){
        System.out.println("User id toDelete: " + userId);
        Bson query = eq("userId", userId);
        try {
            DeleteResult result = applicantsCollection.deleteOne(query);
            System.out.println("Deleted document count: " + result.getDeletedCount());
        } catch (MongoException me) {
            System.err.println("Unable to delete due to an error: " + me);
        }
    }
}