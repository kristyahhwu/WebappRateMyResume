import '../App.css';
import Axios from 'axios';
import React from "react";
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

const Home = () => {  
    const [postList, setPostList] = useState([]);

    useEffect(() => {
      Axios.get(`http://localhost:4321/post/getAll`)
        .then(res => {
          setPostList(res.data)
          console.log('URL: posts/ keyword:')
          console.log("got response:", res)
        }).catch(error => console.log(error))
    }, [])
    
    const handleInit = () => {
      Axios.get(`http://localhost:4321/demo/init`)
        .then(res => {
          console.log(res)
        }).catch(error => console.log(error))
    }
    

    console.log("rendering posts")
    return (
      < div className="App">
        <h1>RateMyResume</h1>
        <h2><Link to="/">home</Link></h2>
        <h2><Link to="/search">search</Link></h2>
        <h2 onClick={handleInit}>Init demo</h2>

        <div> {postList.title}</div>

        {Object.values(postList).map((post) => <p key={post.title}>{post.title} <br/> {post.description}</p>)}
      </div>
    )
  }
  
  export default Home;