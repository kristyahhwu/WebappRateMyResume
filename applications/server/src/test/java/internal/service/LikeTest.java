package internal.service;

import com.google.gson.Gson;
import internal.dataAccess.DTO.HandleCommentDTO;
import internal.dataAccess.DTO.HandleLikeDTO;
import internal.dataAccess.DTO.PostDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LikeTest {

    Gson gson = new Gson();
    @Test
    void like() {
//        Like
//Insert a post into the database
        PostDTO newPost = new PostDTO();
        newPost.author = "Leiyi Gao";
        newPost.title = "Leiyi Gao's resume";
        newPost.description = "Leiyi Gao's resume description";
        String newPostID = Utilities.createPost(gson.toJson(newPost));

//        Call handleLike with the post ID
        HandleLikeDTO likeObj = new HandleLikeDTO();
        likeObj.postid = newPostID;
        likeObj.userid = String.valueOf(1000);
        String likeJson = gson.toJson(likeObj);
        // Count number of likes before insertion
        int numberLikesBefore = Utilities.countNumberOfLikesForPost(newPostID);

        String newLikeID = Utilities.like(likeJson);
        assertFalse(newLikeID.startsWith("Error"));
//        Assert the number like increases (should be 1)
        int numberLikesAfter = Utilities.countNumberOfLikesForPost(newPostID);
        assertTrue(numberLikesAfter > numberLikesBefore);
        assertTrue( numberLikesAfter == 1);
//        Delete the post and the like from the database
        Utilities.deletePost(newPostID);
        Utilities.deleteLike(newLikeID);

    }
}