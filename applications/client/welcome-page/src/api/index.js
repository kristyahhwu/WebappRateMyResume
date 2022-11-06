import axios from 'axios';

const url = 'http://localhost:4321/posts';

export const fetchPosts = () => axios.get(url);
export const createPost = (newPost) => axios.post(url, newPost);