import './App.css';
import React, { useEffect } from 'react';
import { Router, Switch, Route, Routes, Link, Navigate } from "react-router-dom";
import Home from "./Pages/Home";
import Search from "./components/Search"
import { useDispatch } from 'react-redux';

import { getPosts } from './actions/posts'
import Posts from './components/Posts/Posts'
import PostDetail from './Pages/PostDetail';
import Form from './components/Form/Form'
import useStyles from './styles';
import Login from './components/login/Login';
import Navbar from './components/Navbar/Navbar';
import Signup from './components/Registration/Signup';

const App = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getPosts());
  }, [dispatch]);

  return (
    <>
      <Navbar></Navbar>
      <Routes>
        {/* <Route path="/" element={<Home />} /> */}
        <Route path="/" element={<Home />} />
        <Route path="/search" exact element={<Search />} />
        <Route path="/user/login" exact element={<Login />} />
        <Route path="/user/create/" exact element={<Signup />} />
        <Route path="/post/:id" element={<PostDetail />} />
      </Routes>
    </>
  );
}

export default App;
