import './App.css';
import React, { useEffect } from 'react';
import { Router, Switch, Route, Routes, Link, Navigate } from "react-router-dom";
import Home from "./components/Home";
import Search from "./components/Search"
import { Container, AppBar, Typography, Grow, Grid, Box } from '@material-ui/core';
import { useDispatch } from 'react-redux';

import { getPosts } from './actions/posts'
import Posts from './components/Posts/Posts'
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
      <Container maxWidth="lg" display="flex" justifycontent="space-between" >
        <Navbar></Navbar>
        <Grow in>
          <Container>
            <Grid container justifycontent="space-between" alignItems="stretch" spacing={3}>
              <Grid item xs={12} sm={7}>
                {/* <Home></Home> */}
                <Posts />

              </Grid>
              <Grid item xs={12} sm={4}>
                <Form />

              </Grid>
            </Grid>
          </Container>
        </Grow>
      </Container>
      <Routes>
        {/* <Route path="/" element={<Home />} /> */}
        <Route path="/" exact component={() => <Navigate to="/posts" />} />
        <Route path="/search" exact element={<Search />} />
        <Route path="/user/login" exact element={<Login />} />
        <Route path="/user/create/" exact element={<Signup />} />
      </Routes>
    </>
  );
}

export default App;
