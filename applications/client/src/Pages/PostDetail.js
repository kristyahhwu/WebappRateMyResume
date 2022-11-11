import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Typography, TextField, Button } from "@material-ui/core";
import { viewPost, fetchPosts, baseUrl } from "../api";
import { useDispatch} from 'react-redux';
import Axios from 'axios';
import axios from "axios";

const PostDetail = () => {
    console.log(useParams())
    const { id } = useParams();
    const dispatch = useDispatch();
    const [post, setPost] = useState([]);
    const [allComments, setAllComments] = useState([]); // return a list of comments
    const [comment, setComment] = useState(""); // single comment

    // Not working with redux: redux.js:275 Uncaught Error: Actions must be plain objects. Instead, the actual type was: 'Promise'.
    // You may need to add middleware to your store setup to handle dispatching other values, such as 'redux-thunk'
    // to handle dispatching functions
    // useEffect(() => {
    //     dispatch(fetchPosts());
    //   }, [dispatch]);

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
            }).catch(error => console.log(error))
      }, []);

    //TODO: Fix hardcoded userid after login is implemented 
    const likePost = () => {
        Axios.put(`${baseUrl}/post/like`, {
                postid: id,
                userid: '5b9d19ef-67f2-4e53-bd12-2f20eee95230',
        })
    }

    const handleComment = async (e) => {
      e.preventDefault();
      try {
        const { data } = await axios.put(`${baseUrl}/post/comment`,
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
        <div className="imgDiv">
            <div> { post.title } </div>
            <div> { post.description } </div>
            <img src={ post.resumeUrl } />
            <div className = "commentSection">
              {allComments.map((allComments, i) => (
                <Typography key ={i}>
                comment{i}
                </Typography>))}
                <Typography variant="h6">Write a comment</Typography>
                <TextField label="comment" value = {comment} onChange = {(e) => setComment(e.target.value)}></TextField>

                <Button disabled={!comment} onClick={handleComment}>comment</Button>
            </div>
            <button onClick={ likePost }> Like </button>
        </div>
    );
}

export default PostDetail;