package internal.dataAccess.DAO;

import org.bson.types.Binary;

public class TeamMember{
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
