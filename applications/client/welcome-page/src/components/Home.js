import '../App.css';
import Axios from 'axios';
import React from "react";
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

const Home = () => {  
    const [postList, setPostList] = useState([]);

    useEffect(() => {
      Axios.get(`https://jsonplaceholder.typicode.com/posts`)
        .then(res => {
          setPostList(res.data)
          console.log('URL: posts/ keyword:')
          console.log("got response:", res)
        }).catch(error => console.log(error))
    }, [])   
    

    console.log("rendering posts")
    return (
      < div className="App">
        <h1>RateMyResume</h1>
        <h2><Link to="/">home</Link></h2>
        <h2><Link to="/search">search</Link></h2>

        <div> {postList.title}</div>

        {Object.values(postList).map((post) => <p key={post.id}>{post.id} {post.title}</p>)}
      </div>
    )
  }
  
  export default Home;