import React from "react";
import './App.css';
import JSONDATA from './MOCK_DATA.json';
import { useEffect, useState } from 'react';
import Axios from 'axios';

const Home = () => {
    // http://34.94.186.97:3000/
  
    const [searchTerm, setSearchTerm] = useState("");
    const [data, setData] = useState([]);

    useEffect(() => {
      Axios.get('https://jsonplaceholder.typicode.com/posts')
        .then(res => {
          setData(res.data)
        }).catch(error => console.log(error))
    }, [])

    // TODO: 1 -handleSearch send GET request to /post/search with arg as the keyword.
    const handleClick = (arg) => {
      Axios.get('https://jsonplaceholder.typicode.com/posts', {
        data: {
          keyword: arg
        }
      })

      setData([{"id":1,"user":"Farr","post":"this is my resume"}])
    }
    
    console.log(data)
  
  
    return (
      < div className="App">
        <div className="Home">
                {data.map((d) => <li key={d.id}>{d.id} {d.title}</li>)}
        </div>

        <button onClick={handleClick}></button>
  
        <h1>RateMyResume</h1>

        {/*TODO 2 - Modify this so it calls handleSearch. */}
        <input type="text"
          placeholder="Search..."
          onChange={event => {
            setSearchTerm(event.target.value)
          }}/>
  
        {/* {data.filter((data) => {
          if (searchTerm === "") {
            return data;
          } else if (data.user.toLowerCase().includes(searchTerm.toLowerCase())) {
            return data;
          }
          return 0;
        })
          .map(data => {
            return (
              <div className='box' key={data.id}>
                {data.user} <br />  {data.post} <br /> <br />
              </div>
            )
          })
        }
        {arr} */}
      </div>
    )
  }
  
  export default Home;
  
  // can be use for search
  // const handleUpload = async () => {
  //   console.log("Uploading", userId, title );
  //   const body = {
  //     username: userId,
  //     title: title,
  //     notes: notes,
  //   }
  
  //   fetch