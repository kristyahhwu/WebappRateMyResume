import './App.css';
import React from 'react';
import { Router, Switch, Route, Routes, Link } from "react-router-dom";
import Teams from "./Teams";
import Home from "./components/Home";
import Search from "./components/Search"
import { Container, AppBar, Typography, Grow, Grid } from '@material-ui/core';

import image from './images/image.png'
import Posts from './components/Posts/Posts'
import Form from './components/Form/Form'


const App = () => {
  return (
    <>
      <Container maxWidth="lg">
        <AppBar position="static" color="inherit">
          <Typography variant="h2" align="center">Posts</Typography>
          <img src={image} alt="images" height="60" />
        </AppBar>
        <Grow in>
          <Container>
            <Grid Container justify="space-between" alignItems="stretch" spacing={3}>
              <Grid item xs={12} sm={7}>
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
        <Route path="/" element={<Home />} />
        <Route path="/search" element={<Search />} />
      </Routes>
    </>
  );
}

export default App;
