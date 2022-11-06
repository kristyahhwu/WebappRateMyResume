import React from 'react'
import { AppBar, Typography, Button } from '@material-ui/core'
import { Link } from 'react-router-dom';

import logo200 from '../../images/logo200.png';
import useStyles from './styles'
import Search from '../Search';
import Login from '../login/Login.js';


const Navbar = () => {

    const classes = useStyles();
    return (
        <AppBar className={classes.appBar} position="static" color="inherit" >
            <Typography component={Link} to="/" className={classes.heading} sx={{ mr: 2, display: { xs: "none", md: "flex" } }}>
                <img className={classes.image} src={logo200} alt="images" height="80" />
            </Typography>
            <Search className={classes.Search} size="large"></Search>
            <Button variant="contained" color="primary" component={Link} to="/user/login">Login</Button>
            <Button variant="contained" color="default" component={Link} to="/user/create">Register</Button>
        </AppBar>

    )
}

export default Navbar