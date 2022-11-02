package internal.dataAccess.DTO;

public class HandleCommentDTO {
    // will have postid, user (the whole class object, not just id),
    // comment, time as request body.
    public String postid;
    public String userid;
    public String username;
    public String email;
    public String comment;
    public String time;

    public HandleCommentDTO() {
    }

    public HandleCommentDTO(String postid, String userId, String username, String email, String comment, String time) {
        this.postid = postid;
        this.userid = userId;
        this.username = username;
        this.email = email;
        this.comment = comment;
        this.time = time;
    }
}
