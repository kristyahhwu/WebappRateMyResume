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
    public String id;

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

    @Override
    public String toString() {
        return "HandleCommentDTO{" +
                "postid='" + postid + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", time='" + time + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
