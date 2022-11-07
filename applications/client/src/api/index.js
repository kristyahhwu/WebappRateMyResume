import axios from 'axios';

export const baseUrl = 'http://localhost:4321/';

export const fetchPosts = () => axios.get(baseUrl + '/post/getAll');
export const createPost = (newPost) => axios.post(baseUrl + '/post/create', newPost);
export const viewPost = (postid) => axios.get(baseUrl + '/post/view', {
    params: {
        postid: postid
  }});
export const likePost = (id) => axios.put(`${baseUrl}/post/${id}/like`);
