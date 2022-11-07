import axios from 'axios';

const url = 'http://localhost:4321/post';

export const fetchPosts = () => axios.get(url + '/getAll');
export const createPost = (newPost) => axios.post(url + '/create', newPost);
export const viewPost = (postid) => axios.get(url + '/view', {
    params: {
        postid: postid
  }});
export const likePost = (id) => axios.patch(`${url}/${id}/likePost`);
