import React from "react";
import './App.css';
import { useEffect, useState } from 'react';
import Axios from 'axios';

const Home = () => {
    // http://34.94.186.97:3000/
  
    const [data, setData] = useState([]);
    const [id, setId] = useState(1);
    const [databuttonClick, setDataButtonClick] = useState("");

    useEffect(() => {
      Axios.get(`https://jsonplaceholder.typicode.com/posts/${[databuttonClick]}`)
        .then(res => {
          setData(res.data)
        }).catch(error => console.log(error))
    }, [databuttonClick])

    // TODO: 1 -handleSearch send GET request to /post/search with arg as the keyword.
    // const handleSearch = () =>{
    //   setDataButtonClick(id)
    // }

    const handleSearch = () => {
      // Axios.get(`https://jsonplaceholder.typicode.com/posts/${databuttonClick}`, {
      //   data: {
      //     id: arg
      //   }
    // })
    setDataButtonClick(id)

      // setData([{"id":1,"user":"Farr","post":"this is my resume"}])
    }
    
    console.log(data)
  
  
    return (
      < div className="App">
        <h1>RateMyResume</h1>

        {/*TODO 2 - Modify this so it calls handleSearch. */}
        <input type="text"
          placeholder="Search..."
          value = {id}
          onChange={event => {
            setId(event.target.value)
          }}/>

        <button type="button" onClick = {handleSearch}>Search</button>
        <div> {data.title}</div>


        {/* <div className="Home">
                {data.map((d) => <li key={d.id}>{d.id} {d.title}</li>)}
        </div> */}

        {/* <button onClick={handleClick}></button> */}

  
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