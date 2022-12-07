import React, { useState } from 'react';
import { TextField, Button, Typography, Paper } from '@material-ui/core';
import { useDispatch } from 'react-redux';

import useStyles from './FormStyles';
import { createPost } from '../../actions/posts';


/**
 * Form component that allow users to create a post and display on the homepage
 * Takes in inputs {string}: author, title, description, and resumeUrl 
 * Store inputs in database
 * @returns a post with title, thumbnail picture, created time and like button
 */

const Form = () => {
    const [postData, setPostData] = useState({ author: '', title: '', description: '', resumeUrl: '' });
    const classes = useStyles();
    const dispatch = useDispatch();

    const handleSubmit = (e) => {
        e.preventDefault();

        dispatch(createPost(postData));
    }

    const clear = () => {
        setPostData('');
    };

    return (
        <Paper className={classes.paper}>
            <form autoComplete="off" noValidate className={`${classes.root} ${classes.form}`} onSubmit={handleSubmit}>
                <Typography variant="h6" ><strong>Creating a Post</strong></Typography>
                <TextField name="author" variant="outlined" label="Author" fullWidth value={postData.author} onChange={(e) => setPostData({ ...postData, author: e.target.value })} />
                <TextField name="title" variant="outlined" label="Title" fullWidth value={postData.title} onChange={(e) => setPostData({ ...postData, title: e.target.value })} />
                <TextField name="description" variant="outlined" label="Description(max 30 words)" fullWidth value={postData.description} onChange={(e) => setPostData({ ...postData, description: e.target.value })} />
                <TextField name="resumeUrl" variant="outlined" label="Resume Url (jpg/png format)" fullWidth value={postData.resumeUrl} onChange={(e) => setPostData({ ...postData, resumeUrl: e.target.value })} />
                <Button className={classes.buttonSubmit} variant="contained" color="primary" size="large" type="submit" fullWidth>Submit</Button>
                <Button variant="contained" color="secondary" size="small" onClick={clear} fullWidth>Clear</Button>
            </form>

        </Paper>
    );
}

export default Form;