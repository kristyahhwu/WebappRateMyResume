import React from 'react'
import { AppBar, Typography, Button, Toolbar } from '@material-ui/core'
import { Link } from 'react-router-dom';

import logo from '../../images/logo.svg';
import useStyles from './styles'
import Search from '../Search';


const Navbar = () => {

    const classes = useStyles();
    return (
        <AppBar className={classes.appBar} position="static" color="inherit" >
            <Typography component={Link} to="/" className={classes.heading} sx={{ mr: 2, display: { xs: "none", md: "flex" } }}>
                <img className={classes.image} src={logo} alt="images" height="80" />
            </Typography>
            <Toolbar className={classes.toolbar}>
                <Search fullWidth className={classes.Search} size="large"></Search>
                <Button className={classes.submitButton} variant="contained" component={Link} to="/user/login">Login</Button>
                <Button className={classes.submitButton} variant="contained" component={Link} to="/user/create">Register</Button>
            </Toolbar>
        </AppBar>

    )
}

export default Navbar