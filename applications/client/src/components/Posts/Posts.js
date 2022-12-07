import React from 'react';
import { Grid, CircularProgress } from '@material-ui/core';
import Post from './Post/Post';
import { useSelector } from 'react-redux';
import useStyles from './PostsStyles';

const Posts = () => {
    const posts = useSelector((state) => state.posts);
    const classes = useStyles();

    console.log(posts);

    return (
        !posts.length ? <CircularProgress /> : (
            <Grid className={classes.mainContainer} container alignItems="stretch" spacing={2}>
                {posts.map((post) => (
                    <Grid key={post._id} item xs={12} sm={12} md={6} lg={3}>
                        <Post post={post} />
                    </Grid>
                ))}
            </Grid>
        )
    );
}

export default Posts;