import React, { useState } from 'react';
import { TextField, Button, Typography, Paper } from '@material-ui/core';

import useStyles from './styles';

const Form = () => {
    const [postData, setPostData] = useState({ author: '', title: '', description: '', resume: {} });
    const classes = useStyles();

    const handleSubmit = () => {

    }

    const clear = () => {
    };

    return (
        <Paper className={classes.paper}>
            <form autoComplete='off' noValidate className={`${classes.root} ${classes.form}`} onSubmit={handleSubmit}>
                <Typography variant="h6">Creating a Post</Typography>
                <TextField name="author" variant="outlined" label="Author" fullWidth value={postData.author} onchange={(e) => setPostData({ ...postData, author: e.target.value })} />
                <TextField name="title" variant="outlined" label="Title" fullWidth value={postData.title} onchange={(e) => setPostData({ ...postData, title: e.target.value })} />
                <TextField name="description" variant="outlined" label="description" fullWidth value={postData.description} onchange={(e) => setPostData({ ...postData, description: e.target.value })} />
                <div className={classes.fileInput}></div>
                <Button className={classes.buttonSubmit} variant="contained" color="primary" size="large" type="submit" fullWidth>Submit</Button>
                <Button variant="contained" color="secondary" size="small" onClick={clear} fullWidth>Clear</Button>
            </form>

        </Paper>
    );
}

export default Form;