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
          <Grid container style={{ justifyContent: "center" }} alignItems="stretch" spacing={3} className={classes.gridContainer} >
            <Grid
              item xs={12} sm={10} md={10} lg={12} >
              <Posts />
            </Grid>

            {/* <Grid item xs={8} sm={5} md={3} lg={3} fullWidth justifyContent="flex-end" className="createPost-form">
              <Form />
            </Grid> */}
          </Grid>
        </Container>
      </Grow>
    </>
  );
}

export default Home;
