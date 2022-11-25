package internal.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import internal.dataAccess.DTO.HandleCommentDTO;
import internal.dataAccess.DTO.PostDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetCommentsTest {
    Gson gson = new Gson();

    @Test
    void getCommentsForPost() {
//        Get Comments
//        Insert a post with into the database
//        Insert a comment for the database
//        Assert the comment exists for the post
//        Delete the post and the comment from the database

        //Insert a post into the database
        PostDTO newPost = new PostDTO();
        newPost.author = "Leiyi Gao";
        newPost.title = "Leiyi Gao's resume";
        newPost.description = "Leiyi Gao's resume description";


        //  Call handleViewPost with the post ID
        String newPostID = Utilities.createPost(gson.toJson(newPost));
        // Create comment
        HandleCommentDTO comment = new HandleCommentDTO();
        comment.postid = newPostID;
        comment.userid = String.valueOf(1000);
        comment.comment = "Fantastic resume";
        comment.time = "2022-11-17-10-58";
        String commentJson = gson.toJson(comment);

        //Call handleComment with the post ID and a comment
        String newCommentID = Utilities.comment(commentJson);

        String commentsResponse = Utilities.getCommentsForPost(newPostID);
        List<HandleCommentDTO> allCommentsForPost =
                gson.fromJson(commentsResponse, new TypeToken<List<HandleCommentDTO>>(){}.getType());
        boolean foundComment = findComment(newCommentID, allCommentsForPost);
        assertTrue(foundComment);
        //Delete the post and the comment from the database
        Utilities.deletePost(newPostID);
        Utilities.deleteComment(newCommentID);
    }

    private boolean findComment(String newCommentID, List<HandleCommentDTO> allCommentsForPost) {

        for (HandleCommentDTO comment : allCommentsForPost) {
            System.out.println(comment);
            if(comment.id.equals(newCommentID)){
                return true;
            }
        }
        return false;
    }


}