import React, { useEffect, useState } from 'react';
import { Container, AppBar, Typography, Grow, Grid, Box, Paper, TextField, Button } from '@material-ui/core';
import { useDispatch } from 'react-redux';
import { useNavigate } from "react-router-dom";

import useStyles from '../Registration/SignUpStyles';
import { createPost } from '../../actions/posts';
import logo from '../../images/black-logo.png';

const CreatePost = () => {
    const [postData, setPostData] = useState({ author: '', title: '', description: '', resumeUrl: '' });
    const classes = useStyles();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        dispatch(createPost(postData));
        e.target.reset();
        clear();
    }

    const clear = () => {
        setPostData({ author: '', title: '', description: '', resumeUrl: '' });
        console.log("cleared");
    };

    return (
        <Container component='main' maxWidth='xs'>
            <Paper className={classes.paper}>
                <form autoComplete="off" noValidate className={`${classes.root} ${classes.form}`} onSubmit={handleSubmit}>
                    <img className={classes.image} src={logo} alt='images' height="40px" />
                    <Typography className={classes.image} variant="h6" ><strong>Creating a Post</strong></Typography>
                    <TextField name="author" variant="outlined" label="Author" fullWidth value={postData.author} onChange={(e) => setPostData({ ...postData, author: e.target.value })} />
                    <TextField name="title" variant="outlined" label="Title" fullWidth value={postData.title} onChange={(e) => setPostData({ ...postData, title: e.target.value })} />
                    <TextField name="description" variant="outlined" label="Description(max 30 words)" fullWidth value={postData.description} onChange={(e) => setPostData({ ...postData, description: e.target.value })} />
                    <TextField name="resumeUrl" variant="outlined" label="Resume Url (jpg/png format)" fullWidth value={postData.resumeUrl} onChange={(e) => setPostData({ ...postData, resumeUrl: e.target.value })} />
                    <Button className={classes.submit} variant="contained" color="primary" size="large" type="submit" fullWidth>Submit</Button>
                    <Button style={{ marginTop: "10px" }} variant="contained" color="secondary" size="small" onClick={clear} fullWidth>Clear</Button>
                </form>

            </Paper>
        </Container>
    );
}

export default CreatePost;