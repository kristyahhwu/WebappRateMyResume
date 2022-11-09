import './App.css';
import React, { useEffect } from 'react';
import { Container, AppBar, Typography, Grow, Grid, Box } from '@material-ui/core';
import { useDispatch } from 'react-redux';

import { getPosts } from '../actions/posts'
import Posts from '../components/Posts/Posts'
import Form from '../components/Form/Form'
import Navbar from '../components/Navbar/Navbar';

const Home = () => {
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
            <Grid className="classes.mainContainer" container justifycontent="space-between" alignItems="stretch" spacing={3}>
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
    </>
  );
}

export default Home;
