import static com.mongodb.client.model.Filters.*;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.get;


import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.BsonBinarySubType;
import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

//team member collection
class TeamMember{
    String name;
    String role;
    String fileName;
    String email;
    Binary image;

    public TeamMember(String name, String role, String fileName, String email) {
        this.name = name;
        this.role = role;
        this.fileName = fileName;
        this.email = email;
    }
}

class MemberSearchByRoleRequest{
    public String role;
}

class Initializer {
    ArrayList<TeamMember> teamMembers;

    public Initializer() {
        teamMembers = new ArrayList<>();
        TeamMember leiyi = new TeamMember("Leiyi Gao", "Team Leader", "leiyi.jpeg", "lgao4@mail.sfsu.edu");
        TeamMember michael = new TeamMember("Michael Han", "Git Master", "leiyi.jpeg", "mhan2@mail.sfsu.edu");
        TeamMember yinyin = new TeamMember("Yinyin Wu", "Scrum Master", "yinyin.png", "ywu21@mail.sfsu.edu");
        TeamMember justin = new TeamMember("Justin Mao", "Backend Leader", "justin.png", "jmao@mail.sfsu.edu");
        TeamMember nicholas = new TeamMember("Nicholas Hamada", "Frontend Leader", "nicholas.png", "nhamada@mail.sfsu.edu");
        teamMembers.add(leiyi);
        teamMembers.add(michael);
        teamMembers.add(yinyin);
        teamMembers.add(justin);
        teamMembers.add(nicholas);
    }

    public ArrayList<TeamMember> getTeamMembers() {
        return teamMembers;
    }

}

class Applicant{
    /*
    Entity 1: 	Applicant
    Items: 		Each user will have a unique account ID which will be linked to any resumes they
    post and linked to an email provided by the user which is stored in the mongo db database.
    Usage:		authentication, post and comments identification
    Privilege(s): 	basic user
    Attributes:	name, username, creation date, user id, email

     */
     String userId;
     String username;
     LocalDate creationDate;
     String email;

    public Applicant(String userId, String username, LocalDate creationDate, String email) {
        this.userId = userId;
        this.username = username;
        this.creationDate = creationDate;
        this.email = email;
    }
}

class PostSearchByIdRequest{
    String id;
}

class PostDTO{
    String filePath;
    String fileName;
    String postId;
    String postAuthor;
}

class Post{
    /*
    Entity 2: 	Post
    Items:		Each user can post their resume, and the data is also stored in the MongoDB
Database. The resume is also linked with that user, as well as user’s authentication
such as user name, user email etc. Each user can comment on other users’ resume, it could be suggestions, error checking or give out any specific current market information associated with the resume. All this information is also stored in the MongoDb database.
Usage: 	Contains resume, get feedback from other users through comments, templates that
can be used by other users
    Attributes: 	Post id, post date, post author(foreign key to User table use userId), uploaded resume,
    comments, post rating
     */
    String postId;
    LocalDate postDate;
    String postAuthor;
    ObjectId resume;
    String comments;
    int rating;

    public Post(String postId, LocalDate postDate, String postAuthor, ObjectId resume, String comments, int rating) {
        this.postId = postId;
        this.postDate = postDate;
        this.postAuthor = postAuthor;
        this.resume = resume;
        this.comments = comments;
        this.rating = rating;
    }
}

public class SparkServer {

    public static void main(String[] args) {
        port(4321);

        // open connection
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // get ref to database
        MongoDatabase db = mongoClient.getDatabase("UsersDatabase");
        // get ref to collection
        MongoCollection<Document> teamMemberCollection = db.getCollection("teamMemberCollection");
        // get ref to users collection
        MongoCollection<Document> applicantsCollection = db.getCollection("applicantsCollection");
        // get ref to post collections
        MongoCollection<Document> postsCollection = db.getCollection("postsCollection");
        // get ref to resume collection
        MongoCollection<Document> resumeCollection = db.getCollection("resumeCollection");

        Gson gson = new Gson();

        //get all the members and display them on the front end
        get("/loadMembers", (req, res) -> {
            List<Document> members = new ArrayList<>();
            List<Document> doc = teamMemberCollection.find().into(new ArrayList<>());

            for (Document d : doc) {
                members.add(Document.parse(d.toJson()));
            }
            return gson.toJson(members);
        });

        // insert group members for the first time
        get("/initMembers", (req, res) -> {
            Initializer initializer = new Initializer();
            ArrayList<TeamMember> members = initializer.getTeamMembers();
            for (TeamMember member : members) {
                // create the GSON image
                member.image =
                        new Binary(BsonBinarySubType.BINARY, LoadImage(member.fileName));
                // insert into transactions collection
                Document doc = new Document("name", member.name)
                        .append("role", member.role)
                        .append("image", member.image)
                        .append("email", member.email);
                // insert document into collection
                teamMemberCollection.insertOne(doc);
            }
            return "team members initialized";
        });

        //search each member endpoint
        post("/eachMember", (req, res) -> {
            String body = req.body();
            MemberSearchByRoleRequest searchByRoleRequest = gson.fromJson(body, MemberSearchByRoleRequest.class);
            System.out.println("body received"+ body);
            Document search = teamMemberCollection.find(eq("role", searchByRoleRequest.role)).first();
            if (search != null) {// find record where role is x
                System.out.println("member found");

                return gson.toJson(Document.parse(search.toJson()));

            } else {
                //can't find member
                return "User not found";
            }
        });

        get("/posts/", (req, res) -> {// fetches all the posts from database
            List<Document> posts = new ArrayList<>();
            List<Document> doc = postsCollection.find().into(new ArrayList<>());

            for (Document d : doc) {
                posts.add(Document.parse(d.toJson()));
            }
            return gson.toJson(posts);

        });

        post("/posts/", (req, res) -> {// creating a new post pass in an object with many items in it
            String body = req.body();

            PostDTO newPost = gson.fromJson(body, PostDTO.class);

            ObjectId resumeId = upload(newPost.filePath, newPost.fileName, mongoClient);

            // insert into transactions collection
            Document doc = new Document("postId", newPost.postId)
                    .append("postDate", LocalDateTime.now())
                    .append("postAuthor", newPost.postAuthor)
                    .append("resume", resumeId);
            // insert document into collection
            postsCollection.insertOne(doc);
            return "Done";
        });

        post("/posts/search", (req, res) -> {// /posts/search?id=7
            String body = req.body();
            PostSearchByIdRequest searchByIdRequest = gson.fromJson(body, PostSearchByIdRequest.class);
            System.out.println("body received"+ body);
            Document search = postsCollection.find(eq("postId", searchByIdRequest.id)).first();
            if (search != null) {// find record where role is x
                System.out.println("post found");

                return gson.toJson(Document.parse(search.toJson()));

            } else {
                //can't find member
                return "Post not found";
            }
        });

        post("/posts/user", (req, res) -> {// creating a new user

            String body = req.body();
            Applicant newUser = gson.fromJson(body, Applicant.class);

            // insert into transactions collection
            Document doc = new Document("userId", newUser.userId)// should be auto generated by mongo db
                    .append("username", newUser.username)
                    .append("creationDate", LocalDateTime.now())
                    .append("email", newUser.email);
            // insert document into collection
            applicantsCollection.insertOne(doc);

            return "User created";
        });

    }

    public static byte[] LoadImage(String filePath) throws Exception {
        File file = new File(filePath);
        int size = (int)file.length();
        byte[] buffer = new byte[size];
        FileInputStream in = new FileInputStream(file);
        in.read(buffer);
        in.close();
        return buffer;
    }

    public static ObjectId upload(String filePath,String fileName, MongoClient mongo) {
        ObjectId fileId = null;
        try {
            MongoDatabase imgDb = mongo.getDatabase("imageDatabase");

// Create a gridFSBucket
            GridFSBucket gridBucket = GridFSBuckets.create(imgDb);


            InputStream inStream = new FileInputStream(new File(filePath));

// Create some customize options for the details that describes
// the uploaded image
            GridFSUploadOptions uploadOptions = new GridFSUploadOptions().chunkSizeBytes(1024).metadata(new Document("type", "image").append("content_type", "image/png"));

            fileId = gridBucket.uploadFromStream(fileName, inStream, uploadOptions);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongo.close();
        }
        return fileId;
    }

}
//how to process request body
//example:
// get a team member by role
//

