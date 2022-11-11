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
        get("/post/search", handler.handleSearch);
        post("/post/create", handler.handleCreatePost);

        put("/post/like", handler.handleLike);
        put("/post/comment", handler.handleComment);

        get("/post/view", handler.handleViewPost);
        get("/post/view/like", handler.handleNumberOfLikes);
        get("/post/view/comments", handler.handleGetCommentsForPost);

        post("/user/login", handler.handleLogin);
        post("/user/logout", handler.handleLogout);
        post("/user/create/", handler.handleCreateUser);

        get("/demo/init", handler.handleInitDemo);

        //--------------------------------------------------------------------------------------------------------------



    }


}


