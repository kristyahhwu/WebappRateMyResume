import './App.css';
import React, { useEffect } from 'react';
import { Router, Switch, Route, Routes, Link } from "react-router-dom";
import Teams from "./Teams";
import Home from "./components/Home";
import Search from "./components/Search"
import { Container, AppBar, Typography, Grow, Grid, Box } from '@material-ui/core';
import { useDispatch } from 'react-redux';

import { getPosts } from './actions/posts'
import logo200 from './images/logo200.png'
import Posts from './components/Posts/Posts'
import Form from './components/Form/Form'
import useStyles from './styles';
import Login from './login/Login';

const App = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getPosts());
  }, [dispatch]);

  return (
    <>
      <Container maxWidth="lg" display="flex" justifycontent="space-between" >
        <AppBar className={classes.appBar} position="static" color="inherit" >
          <Typography className={classes.heading} sx={{ mr: 2, display: { xs: "none", md: "flex" } }}>
            <img className={classes.image} src={logo200} alt="images" height="80" />
          </Typography>
          <Search className={classes.Search}></Search>
          <Login className={classes.button}></Login>
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
      {/* <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/search" element={<Search />} />
      </Routes> */}
    </>
  );
}

export default App;
