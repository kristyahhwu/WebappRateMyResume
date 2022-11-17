package internal.service;
import com.google.gson.Gson;
import internal.dataAccess.DTO.PostDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class createPostTest {
    Gson gson = new Gson();
    @Test
    void createPost(){
        PostDTO newPost = new PostDTO();
        newPost.author = "John Johnson";
        newPost.title = "Test Resume";
        newPost.description = "Description";
        String newPostID = Utilities.createPost(gson.toJson(newPost));
        assertNotEquals("Post not found",Utilities.viewPost(newPostID));
        String fetchPost = Utilities.viewPost(newPostID);
        PostDTO fetchObj = gson.fromJson(fetchPost, PostDTO.class);
        assertEquals(newPost.title, fetchObj.title);
        Utilities.deletePost(newPostID);
        assertEquals("Post not found", Utilities.viewPost(newPostID));
    }
}
