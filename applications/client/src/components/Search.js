import '../App.css';
import Axios from 'axios';
import React from "react";
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import SearchBar from "material-ui-search-bar";

// TODO: search bar function only works with <form> now
// https://www.youtube.com/watch?v=8tZtm-znc9A video on Redux Toolkit filter

/**
 * function to filter posts using keyword
 * @param keyword anyword match in the description
 * @returns a lists of posts with input keyword
 */

const Search = () => {
  const [postList, setPostList] = useState([]);

  const handleSearch = searchEvent => {
    const inputKeyword = searchEvent.target.keyword.value
    searchEvent.preventDefault()
    //   Axios({
    //     method: 'post',
    //     url: 'http://localhost:4321/post/search',
    //     data: {
    //       firstName: 'Fred',
    //       lastName: 'Flintstone'
    //     }})
    Axios.get(`http://localhost:4321/post/search`, {
      // Axios.get(`http://34.94.186.97:4321/post/search`, {
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
      {/* <h1>RateMyResume</h1>
      <h2><Link to="/">home</Link></h2>
      <h2><Link to="/search">search</Link></h2> */}
      <SearchBar type="text" placeholder="Search..." onSubmit={handleSearch}>
        <input type="text" name="keyword"></input>
        <input type="submit" value="Search"></input>
      </SearchBar>

      <div> {postList.title}</div>

      {Object.values(postList).map((post) => <p key={post.title}>{post.title} <br /> {post.description}</p>)}
    </div>
  )
}

export default Search;
