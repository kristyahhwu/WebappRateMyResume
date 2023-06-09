import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { fetchPosts, baseUrl } from "../api";
import { useDispatch } from 'react-redux';
import Axios from 'axios';

// All MUI materials import
import Face2Icon from '@mui/icons-material/Face2';
import Face4Icon from '@mui/icons-material/Face4';
import InsertCommentIcon from '@mui/icons-material/InsertComment';
import ThumbUpAltIcon from '@material-ui/icons/ThumbUpAlt';
import { textAlign } from "@mui/system";
import { Typography, TextField, Button, Paper, Grid, Avatar, Box, InputAdornment, IconButton, Card, CardHeader, CardContent } from "@material-ui/core";

import './pages.css';
import useStyles from "../Pages/styles.js"

const PostDetail = () => {
  const { id: currentPostId } = useParams();

  const dispatch = useDispatch();
  const classes = useStyles();

  const [post, setPost] = useState([]);
  const [allComments, setAllComments] = useState([]);
  const [comment, setComment] = useState("");
  const [likes, setLikes] = useState(0);
  //TODO: Fix hardcoded userid after login is implemented 
  const currentUserId = '';
  const currentUsername = 'gator';

  useEffect(() => {
    Axios.get(`${baseUrl}/post/view`, {
      params: {
        postid: currentPostId
      }
    })
      .then(res => {
        setPost(res.data)
        console.log('URL: /post/view postid:', currentPostId)
        console.log("response:", res.data)
        getLikes()
      }).catch(error => console.log(error));
    Axios.get(`${baseUrl}/post/view/comments`, {
      params: {
        postid: currentPostId
      }
    })
      .then(res => {
        console.log('URL: /post/view/comments postid:', currentPostId)
        console.log("response:", res.data)
        setAllComments(res.data)
      }).catch(error => console.log(error))
  }, []);


  const likePost = () => {
    Axios.put(`${baseUrl}/post/like`, {
      postid: currentPostId,
      userid: currentUserId,
    })
  }

  const getLikes = () => {
    Axios.get(`${baseUrl}/post/view/like`, {
      params: {
        postid: currentPostId
      }
    })
      .then(res => {
        setLikes(res.data)
      }).catch(error => console.log(error))
  }

  const handleComment = async (e) => {
    e.preventDefault();
    try {
      const { data } = await Axios.put(`${baseUrl}/post/comment`,
        {
          postid: currentPostId,
          userid: currentUserId,
          username: currentUsername,
          comment: comment,
        })
      setComment("");
      fetchPosts();
    }
    catch (error) {
      console.log(error);
    }
  };

  console.log(currentPostId)

  return (
    <div className="postDetailsRootDiv">
      <Paper className={classes.leftSide}
        elevation={6}>
        <div className="postDetailsLeft">
          <div className="postTitle"> {post.title} </div>
          <div className="postDescription"> {post.description} </div>
          <div>
          <Button size="small" color="primary" onClick={likePost}>
              <ThumbUpAltIcon fontSize="small" />
              &nbsp; {likes} &nbsp;              
            </Button>
          </div>
          <img className="resume" src={post.resumeUrl} alt={post.title} />
        </div>
      </Paper>


      <div className="commentSection">
        <Paper className={classes.paper} elevation={6}>
          <Grid className={classes.paper}>
            <Typography variant="h5" mb={2} >
              <strong>Comments</strong>
            </Typography>

            <TextField fullWidth minRows={1}
              variant="outlined"
              multiline value={comment}
              onChange={(e) => setComment(e.target.value)}
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">
                    <IconButton edge="end" color="primary">
                      <InsertCommentIcon />
                    </IconButton>
                  </InputAdornment>
                ),
              }}>
            </TextField>
            <Button className={classes.button} variant="contained" fullWidth disabled={!comment} onClick={handleComment}>comment</Button>
            {/* <Typography variant="h6">Write a comment</Typography> */}
            {/* <Grid container wrap="nowrap" spacing={2}>
            <Grid item>
              <Avatar>
                <Face2Icon color="error" />
                <Face4Icon sx={{ color: "#FC0" }} ></Face4Icon>
              </Avatar>
            </Grid>
            <Grid justifyContent="left" item xs zeroMinWidth>
              <h4 style={{ margin: 0, textAlign: "left" }}>first last</h4>
              <p className="p" style={{ textAlign: "left" }}>
                less uglycomaslidfjalsjfiasjfoiweuroiwueowrtoiejrgoisjuoifjsadoifasudfhaisuyrpiuwyehfipuawhspifuwahsdofment
              </p>
              <p style={{ textAlign: "left", color: "gray" }}>
                posted 1 minute ago
              </p>
            </Grid>
          </Grid>
          <Divider variant="fullWidth" style={{ margin: "30px 0" }} />
          <Grid container wrap="nowrap" spacing={2}>
            <Grid item>
              <Avatar>
                <Face4Icon sx={{ color: "#FC0" }} fontSize="large"></Face4Icon>
              </Avatar>
            </Grid>
            <Grid justifyContent="left" item xs zeroMinWidth>
              <h4 style={{ margin: 0, textAlign: "left" }}>mingzi xingshi</h4>
              <p style={{ textAlign: "left" }}>
                another sample
              </p>
              <p style={{ textAlign: "left", color: "gray" }}>
                posted 1 minute ago
              </p>
            </Grid>
          </Grid> */}
          </Grid>

          <Grid className={classes.paper}>
            {allComments.map((allComments, idx) => (
              <Typography gutterBottom key={idx}>
                  <Card sx={{ width: 1 }}>                
                    <CardHeader
                      avatar = {
                        <Avatar>
                          <Face4Icon />
                        </Avatar >
                      }
                      title={allComments.username}
                      subheader={allComments.time} 
                    />
                    <CardContent>
                      <Typography paragraph> {allComments.comment} </Typography> <br />
                    </CardContent>
                  </Card>                
              </Typography>
            ))}
          </Grid>
        </Paper>
      </div >

    </div >
  );
}

export default PostDetail;