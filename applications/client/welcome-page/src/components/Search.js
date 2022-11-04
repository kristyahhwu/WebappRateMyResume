import '../App.css';
import Axios from 'axios';
import React from "react";
import { Link } from 'react-router-dom';
import { useEffect, useState } from 'react';
import SearchBar from "material-ui-search-bar";

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
<<<<<<< HEAD
      Axios.get(`http://34.94.186.97:4321/post/search`, {
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
=======
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
>>>>>>> d41d5132 (update navbar, create logo for our app, working on login)

  console.log("rendering posts")
  return (
    < div className="App">
      {/* <h1>RateMyResume</h1>
      <h2><Link to="/">home</Link></h2>
      <h2><Link to="/search">search</Link></h2> */}
      <form type="text" placeholder="Search..." onSubmit={handleSearch}>
        <input type="text" name="keyword"></input>
        <input type="submit" value="Search"></input>
      </form>

      <div> {postList.title}</div>

      {Object.values(postList).map((post) => <p key={post.title}>{post.title} <br /> {post.description}</p>)}
    </div>
  )
}

<<<<<<< HEAD
        {Object.values(postList).map((post) => <p key={post.title}>{post.title} <br/> {post.description}</p>)}
      </div>
    )
  }
  
  export default Home;
=======
export default Search;
>>>>>>> d41d5132 (update navbar, create logo for our app, working on login)
