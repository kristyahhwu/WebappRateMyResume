import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Typography, TextField, Button } from "@material-ui/core";
import { viewPost, fetchPosts, baseUrl } from "../api";
import { useDispatch} from 'react-redux';
import Axios from 'axios';

import './pages.css';

const PostDetail = () => {
    console.log(useParams())
    const { id } = useParams();

    const dispatch = useDispatch();

    const [post, setPost] = useState([]);
    const [allComments, setAllComments] = useState([]);
    const [comment, setComment] = useState("");
    const [likes, setLikes] = useState(0);

    useEffect(() => {
        Axios.get(`${baseUrl}/post/view`, {
            params: {
              postid: id
            }
          })
            .then(res => {
              setPost(res.data)
              console.log('URL: /post/view postid:', id)
              console.log("response:", res)
              getLikes()
            }).catch(error => console.log(error))
      }, []);

    //TODO: Fix hardcoded userid after login is implemented 
    const likePost = () => {
        Axios.put(`${baseUrl}/post/like`, {
                postid: id,
                userid: '5d7c8cb9-b3e8-4b86-8b0b-704fac91d553',
        })
    }

    const getLikes = () => {
      Axios.get(`${baseUrl}/post/view/like`, {
        params: {
          postid: id
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
          postid: post.id,
          comment,
        });
        setComment("");
        fetchPosts();
      } 
      catch (error){
        console.log(error);
      }
    };

    console.log(id)

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
          {allComments.map((allComments, i) => (
            <Typography key ={i}>
            comment{i}
            </Typography>))}
            <Typography variant="h6">Write a comment</Typography>
            <TextField label="comment" value = {comment} onChange = {(e) => setComment(e.target.value)}></TextField>

            <Button disabled={!comment} onClick={handleComment}>comment</Button>
        </div>
      </div>
    );
}

export default PostDetail;