package internal.service;
import com.google.gson.Gson;
import internal.dataAccess.DTO.PostDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class createPostTest {
    Gson gson = new Gson();
    @Test
    void createPost(){
        //create PostDTO object and insert into database
        PostDTO newPost = new PostDTO();
        newPost.author = "John Johnson";
        newPost.title = "Test Resume";
        newPost.description = "Description";
        String newPostID = Utilities.createPost(gson.toJson(newPost));

        //check that post exists in database
        assertNotEquals("Post not found",Utilities.viewPost(newPostID));

        //fetch post from database and compare title with original DTO
        String fetchPost = Utilities.viewPost(newPostID);
        PostDTO fetchObj = gson.fromJson(fetchPost, PostDTO.class);
        assertEquals(newPost.title, fetchObj.title);

        //delete post from collection and test to make sure it is deleted
        Utilities.deletePost(newPostID);
        assertEquals("Post not found", Utilities.viewPost(newPostID));
    }
}
