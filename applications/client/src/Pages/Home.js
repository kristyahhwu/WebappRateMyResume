import React, { useEffect } from 'react';
import { Container, AppBar, Typography, Grow, Grid, Box } from '@material-ui/core';
import { useDispatch } from 'react-redux';

import { getPosts } from '../actions/posts'
import Posts from '../components/Posts/Posts'
import Form from '../components/Form/Form'
import useStyles from '../Pages/styles.js'

const Home = () => {
  const dispatch = useDispatch();
  const classes = useStyles();

  useEffect(() => {
    dispatch(getPosts());
  }, [dispatch]);

  return (
    <>
      <Grow in>
        <Container maxWidth="xl">
          <Grid container justifyContent="space-between" alignItems="stretch" spacing={3} >
            <Grid item xs={12} sm={10} md={5} lg={6}>
              {/* <Home></Home> */}
              <Posts />

            </Grid>

            <Grid item xs={12} sm={5} md={3} className="createPost-form">
              <Form />
            </Grid>
          </Grid>
        </Container>
      </Grow>
    </>
  );
}

export default Home;
