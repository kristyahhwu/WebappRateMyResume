package internal.dataAccess.DAO;

import java.time.LocalDate;

public class User {
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
    String password;

    public User(){

    }

    public User(String userId, String username, LocalDate creationDate, String email) {
        this.userId = userId;
        this.username = username;
        this.creationDate = creationDate;
        this.email = email;
    }
}
