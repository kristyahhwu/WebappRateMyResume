import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { viewPost, fetchPosts, baseUrl } from "../api";
import { useDispatch} from 'react-redux';
import Axios from 'axios';

const PostDetail = () => {
    console.log(useParams())
    const { id } = useParams();
    const dispatch = useDispatch();
    const [post, setPost] = useState([]);

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

    const likePost = () => {
        Axios.put(`${baseUrl}/post/like`, {
            body: {
                postid: id,
                userid: 'aaa',
            }
        })
    }

    console.log(id)

    return (
        <div className="imgDiv">
            <div> { post.title } </div>
            <div> { post.description } </div>
            <button onClick={ likePost }> Like </button>
        </div>
    );
}

export default PostDetail;