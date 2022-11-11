package internal.service;

import com.google.gson.Gson;
import internal.dataAccess.DTO.PostDTO;
import static org.junit.jupiter.api.Assertions.*;

class ViewPostTest {

    Gson gson = new Gson();

    @org.junit.jupiter.api.Test
    void viewPost() {
//        View a post
//        Insert a post into the database
        PostDTO newPost = new PostDTO();
        newPost.postAuthor = "Leiyi Gao";
        newPost.filePath = "/Users/gaolarry/Downloads/";
        newPost.fileName = "Leiyi Gao.pdf";
        newPost.title = "Leiyi Gao's resume";
        newPost.description = "Leiyi Gao's resume description";


//        Call handleViewPost with the post ID
        String newPostID = Utilities.createPost(gson.toJson(newPost));
//        Assert the post exists with the same details that we gave
        String searchResponse = Utilities.viewPost(newPostID);

        assertNotEquals("Post not found", searchResponse);
        PostDTO responseDTO = gson.fromJson(searchResponse, PostDTO.class);
        assertEquals(newPost.title, responseDTO.title);
//        Delete the post from the database

    }
}