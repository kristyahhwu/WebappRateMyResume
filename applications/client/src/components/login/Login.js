import React, { useEffect } from "react";
import axios from "axios";
import { Paper, Container, Button, Grid, Typography, TextField, InputAdornment } from "@material-ui/core";

// The followings are all the imported images/icons
import useStyles from "../Registration/SignUpStyles";
import logo from '../../images/black-logo.png'
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import LockIcon from '@mui/icons-material/Lock';

const Login = () => {
    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");
    const classes = useStyles();

    function handleResponse(response) {
        console.log("Encoded JWT ID token: " + response.credential); // grab JWT token, need to decode
    }

    useEffect(() => {
        /* global google */
        google.accounts.id.initialize({
            client_id: "654864362395-75apagrj9har8nqh77pnm1s72064k46h.apps.googleusercontent.com",
            callback: handleResponse
        });

        google.accounts.id.renderButton(
            document.getElementById("googleLoginBtn"),
            { theme: "outline", size: "large" }
        )
    }, []);

    const handleSubmit = () => {
        console.log(username);
        console.log(password);
        let data = {
            username: username,
            password: password,
        };
        let jsonedData = JSON.stringify(data);
        console.log(data);
        console.log(jsonedData)
        let config = {
            method: "post",
            url: "/user/login",
            data: jsonedData,
        };

        axios(config)
            .then((response) => {
                console.log("response: " + response.data);
                console.log(JSON.stringify(response.data));
            })
            .catch((error) => {
                console.log("error: " + error);
            });
    };



    return (

        <Container component='main' maxWidth='xs'>
            <Paper className={classes.paper} elevation={3}>
                <img className={classes.image} src={logo} alt='images' height="40px" />
                <Typography variant="h5"><strong> Welcome </strong></Typography>
                <form className={classes.form} onSubmit={(e) => handleSubmit(e)}>
                    <div className={classes.form}>
                        <TextField
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <PersonOutlineOutlinedIcon />
                                    </InputAdornment>
                                ),
                            }}
                            variant="outlined" size="medium"
                            className={classes.form}
                            type="username"
                            placeholder="Enter your username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    </div>
                    <div className={classes.form}>
                        <TextField
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <LockIcon />
                                    </InputAdornment>
                                ),
                            }}
                            variant="outlined" size="medium"
                            className={classes.form}
                            type="password"
                            name="password"
                            placeholder="Enter your password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>

                    <Grid container justifyContent='center'>
                        <Button className={classes.submit} variant="outlined" size="large" onClick={handleSubmit} type="button">
                            Log In
                        </Button>

                        <div id='googleLoginBtn'></div>
                    </Grid>

                    <Grid container justifyContent='center'>
                        Don't have an account? Register <a href="/user/create/"> <strong>  Here </strong></a>
                    </Grid>

                </form>
            </Paper>
            {/* <Button variant="outlined" size="large" color="primary">
                Login
            </Button>
            <Button variant="contained" size="large" color="primary">
                Sign Up
            </Button> */}
        </Container>


    );

};

export default Login;