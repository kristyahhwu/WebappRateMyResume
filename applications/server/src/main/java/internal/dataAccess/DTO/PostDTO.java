package internal.dataAccess.DTO;

public class PostDTO{
    public String filePath;
    public String fileName;
    public String title;
    public String postId;// auto-generated by mongodb
    public String description;
    public String author;// id of the user that posted connected with Applicant userId
    public String resumeUrl;

}
