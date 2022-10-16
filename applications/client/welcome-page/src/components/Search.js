import '../App.css';
import Axios from 'axios';
import React from "react";
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';

const SearchPage = () => {  
    const [postList, setPostList] = useState([]);

    const handleSearch = searchEvent =>{
      const inputKeyword = searchEvent.target.keyword.value
      Axios.get(`http://localhost:4321/post/search`, {
        params: {
          keyword: inputKeyword
        }
      })
        .then(res => {
          setPostList(res.data)
          console.log('URL: /post/search keyword:', inputKeyword)
          console.log("response:", res)
        }).catch(error => console.log(error))
    }    

    console.log("rendering posts")
    return (
      < div className="App">
        <h1>RateMyResume</h1>
        <h2><Link to="/">home</Link></h2>
        <h2><Link to="/search">search</Link></h2>

        <form type="text" placeholder="Search..." onSubmit={handleSearch}>
            <input type="text" name="keyword"></input>
            <input type="submit" value="Search"></input>
        </form>

        <div> {postList.title}</div>

        {Object.values(postList).map((post) => <p key={post.id}>{post.id} {post.title}</p>)}
      </div>
    )
  }
  
  export default SearchPage;