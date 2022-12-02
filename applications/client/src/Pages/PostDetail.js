import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Typography, TextField, Button } from "@material-ui/core";
import { viewPost, fetchPosts, baseUrl } from "../api";
import { useDispatch} from 'react-redux';
import Axios from 'axios';

import './pages.css';

const PostDetail = () => {
    const { id: currentPostId } = useParams();

    const dispatch = useDispatch();

    const [post, setPost] = useState([]);
    const [allComments, setAllComments] = useState([]);
    const [comment, setComment] = useState("");
    const [likes, setLikes] = useState(0);
    //TODO: Fix hardcoded userid after login is implemented 
    const currentUserId='';
    const currentUsername='gator';

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
        });
        setComment("");
        fetchPosts();
      } 
      catch (error){
        console.log(error);
      }
    };

    console.log(currentPostId)

    return (
      <div className="postDetailsRootDiv">
        <div className="postDetailsLeft">
          <div> { post.title } </div>
          <div> { likes } </div>
          <button onClick={ likePost }> Like </button>
          <div> { post.description } </div>
          <img className="resume" src={ post.resumeUrl } />
        </div>

        <div className = "commentSection">
          <Typography variant="h6">Write a comment</Typography>
          <TextField label="comment" value = {comment} onChange = {(e) => setComment(e.target.value)}></TextField>
          <Button disabled={!comment} onClick={handleComment}>comment</Button>

          
          {allComments.map((allComments, i) => (
            <Typography key ={i}>
              {allComments.username} <br />
              {allComments.time} <br />
              {allComments.comment} <br />
              <br />
            </Typography>))}
            
        </div>
      </div>
    );
}

export default PostDetail;