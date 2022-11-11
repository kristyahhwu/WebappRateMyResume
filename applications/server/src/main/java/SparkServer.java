import static spark.Spark.*;
import static spark.Spark.before;
import internal.service.RoutesHandler;


public class SparkServer {

    public static void main(String[] args) {
        RoutesHandler handler = new RoutesHandler();

        port(4321);

        //Set CORS policy
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
        options("/*", handler.handlePreflight);

        get("/", handler.handleHome);

        get("/post/getAll", handler.handleGetAll);
        get("/post/search", handler.handleSearch);// returns a collections of Posts, uses keyword
        post("/post/create", handler.handleCreatePost);

        get("/post/view", handler.handleViewPost); // will have postid in the query parameter.
                                                        // See handleSearch for example, returns a single Post
        put("/post/like", handler.handleLike); // will have postid, userid in the request body
                                                        // (not query parameters).
        put("/post/comment", handler.handleComment); // will have postid, user (the whole class object, not just id),
                                                            // comment, time as request body.

        post("/user/login", handler.handleLogin); // Do this last. Might not need this at all
        post("/user/logout", handler.handleLogout); // Do this last. Might not need this at all
        post("/user/create/", handler.handleCreateUser);

        get("/demo/init", handler.handleInitDemo);

        get("/post/comment", handler.handleGetCommentsForPost);// fetches all comments for a post whose postid is passed in
                                                                // as a query parameter
        get("/post/view/like", handler.handleNumberOfLikes); // retrieves number of likes for a post whose postid is passed in
                                                                // as a query parameter
        //--------------------------------------------------------------------------------------------------------------



    }


}


