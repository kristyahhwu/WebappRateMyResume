import React from "react";
import './App.css';
import JSONDATA from './MOCK_DATA.json';
import { useEffect, useState } from 'react';
import Axios from 'axios';

const Home = () => {

    // helper func needed displayResumeSnippets()
    // http://34.94.186.97:3000/
  
    const [searchTerm, setSearchTerm] = useState("");
    const [data, setData] = useState([]);
  
    useEffect(() => {
      Axios.get('https://jsonplaceholder.typicode.com/posts')
        .then(res => {
          console.log(res.data)
          setData(res.data)
        }).catch(error => console.log(error))
    }, [])
  
    const arr = data.map((data) => {
      return (
        <tr>
          <td>{data.user}</td>
          <td>{data.post}</td>
        </tr>
      )
    })
  
  
    return (
      < div className="App">
  
        <h1>RateMyResume</h1>
        <input type="text"
          placeholder="Search..."
          onChange={event => {
            setSearchTerm(event.target.value)
          }}
        />
  
        {JSONDATA.filter((data) => {
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
        {arr}
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