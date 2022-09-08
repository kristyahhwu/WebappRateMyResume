import static com.mongodb.client.model.Filters.*;
import static spark.Spark.*;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.BsonBinarySubType;
import org.bson.Document;
import org.bson.types.Binary;

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

class Initializer{
    ArrayList<TeamMember> teamMembers;

    public Initializer(){
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

public class Spark {

    public static void main(String[] args) {
        port(4321);

        // open connection
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        // get ref to database
        MongoDatabase db = mongoClient.getDatabase("UsersDatabase");
        // get ref to collection
        MongoCollection<Document> teamMemberCollection = db.getCollection("teamMemberCollection");

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

}
