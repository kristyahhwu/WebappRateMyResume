package internal.dataAccess.DAO;

import org.bson.types.ObjectId;

import java.time.LocalDate;

public class Post{
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
    String title;
    String description;
    LocalDate postDate;
    String postAuthor;
    ObjectId resume;
    String comments;
    int rating;

    public Post(){

    }

    public Post(postConstructorParams param) {
        this.postId = param.postId;
        this.title = param.title;
        this.description = param.description;
        this.postDate = param.postDate;
        this.postAuthor = param.postAuthor;
        this.resume = param.resume;
        this.comments = param.comments;
        this.rating = param.rating;
    }
    class postConstructorParams {
        String postId;
        String title;
        String description;
        LocalDate postDate;
        String postAuthor;
        ObjectId resume;
        String comments;
        int rating;
    }

}
