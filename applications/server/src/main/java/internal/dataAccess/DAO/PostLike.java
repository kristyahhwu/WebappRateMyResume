package internal.dataAccess.DAO;

public class PostLike {
    public String id;
    public String postid;
    public String userid; // WhoLikedThePost

    public PostLike(String id, String postid, String userid) {
        this.id = id;
        this.postid = postid;
        this.userid = userid;
    }

    public PostLike() {// it could come in handy
    }

}
