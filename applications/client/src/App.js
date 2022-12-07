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
import Login from './Pages/Login';
import Navbar from './components/Navbar/Navbar';
import Signup from './components/Registration/Signup';
import CreatePost from './components/Form/CreatePost';

/**
 * Keeps all page navigation within the session,
 * define routes for components and elements
 * @returns a container for all other components
 */

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
        <Route path="/" element={<Home />} />
        <Route path="/search" exact element={<Search />} />
        <Route path="/user/login" exact element={<Login />} />
        <Route path="/user/create/" exact element={<Signup />} />
        <Route path="/post/:id" element={<PostDetail />} />
        <Route path="/post/create" element={<CreatePost />} />
      </Routes>
    </>
  );
}

export default App;
