import './App.css';
import React, { useEffect } from 'react';
import { Router, Switch, Route, Routes, Link } from "react-router-dom";
import Teams from "./Teams";
import Home from "./components/Home";
import Search from "./components/Search"
import { Container, AppBar, Typography, Grow, Grid } from '@material-ui/core';
import { useDispatch } from 'react-redux';

import { getPosts } from './actions/posts'
import image from './images/image.png'
import Posts from './components/Posts/Posts'
import Form from './components/Form/Form'
import useStyles from './styles';

const App = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getPosts());
  }, [dispatch]);

  return (
    <>
      <Container maxWidth="lg">
        <AppBar className={classes.appBar} position="static" color="inherit">
          <Typography className={classes.heading} variant="h2" align="center">Posts</Typography>
          <img className={classes.image} src={image} alt="images" height="60" />
        </AppBar>
        <Grow in>
          <Container>
            <Grid container justifyContent="space-between" alignItems="stretch" spacing={3}>
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
