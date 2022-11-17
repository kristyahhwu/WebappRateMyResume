package internal.service;

import com.google.gson.Gson;
import internal.dataAccess.DTO.HandleLikeDTO;
import internal.dataAccess.DTO.PostDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NumberOfLikesTest {
    Gson gson = new Gson();

    @Test
    void numberOfLikes() {
//        Get No. Of likes
//        Insert a post with xx number of likes into the database
//        Call getNoOfLikesForPost with the post ID
//        Assert the number like is correct
//        Delete the post and the like from the database

        PostDTO newPost = new PostDTO();
        newPost.postAuthor = "Leiyi Gao";
        newPost.title = "Leiyi Gao's resume";
        newPost.description = "Leiyi Gao's resume description";
        newPost.filePath = "/Users/gaolarry/Downloads";
        newPost.fileName = "Leiyi Gao.pdf";
        String newPostID = Utilities.createPost(gson.toJson(newPost));

        // Insert two likes to database for post
        HandleLikeDTO likeObj = new HandleLikeDTO();
        likeObj.postid = newPostID;
        likeObj.userid = String.valueOf(1000);
        String likeJson = gson.toJson(likeObj);
        String newLikeID = Utilities.like(likeJson);


        Assert.assertFalse(newLikeID.startsWith("Error"));
//        Assert the number like increases (should be 1)
        int numberLikes = Utilities.countNumberOfLikesForPost(newPostID);
        Assert.assertTrue( numberLikes == 1);
//        Delete the post and the like from the database
        Utilities.deletePost(newPostID);
        Utilities.deleteLike(newLikeID);

    }
}