import React from "react";
import { Paper, Container, Button, Grid, Typography, TextField, InputAdornment, Box } from "@material-ui/core";
import axios from "axios";

// The followings are all the imported images/icons
import PersonOutlineOutlinedIcon from '@mui/icons-material/PersonOutlineOutlined';
import EmailOutlinedIcon from '@mui/icons-material/EmailOutlined';
import LockIcon from '@mui/icons-material/Lock';
import logo from '../../images/black-logo.png';

import validate from "./Validate";
import useStyles from "./SignUpStyles";
import { baseUrl } from "../../api";

/**
 * Signup component for users to create an account
 * @param {string} username - username to register for an account
 * @param {string} password - password > 8 characters and at least 1 digit
 * @param {string} email - email must obey regular expressions rule
 * @returns user object created in database
 */

const Signup = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");
  const [email, setEmail] = React.useState("");
  const [passwordShown, setPasswordShown] = React.useState(false); // dont show password at first
  const [passwordReqShow, setPasswordReqShow] = React.useState(false);
  const classes = useStyles();

  let errors = {

  };

  const handlePasswordInputFocus = () => {
    setPasswordReqShow(true);
  };

  // after ShowPassword being called
  // reverse bool state of passwordShown
  const ShowPassword = (e) => {
    e.preventDefault();
    setPasswordShown(!passwordShown);
  };

  const handleSubmit = (e) => {
    console.log("username:" + username);
    console.log("password:" + password);
    const data = {
      username: username,
      email: email,
      password: password,
    };

    // clear fields after submitting
    setUsername('');
    setPassword('');
    setEmail('');


    errors = validate(data);
    console.log("errors: ", errors);


    if ((errors.username).length > 0 || (errors.email).length > 0 || (errors.password).length > 0) {
      let passwordValidatedMessage = "";
      e.preventDefault();
      if ((errors.password).length > 0) {
        passwordValidatedMessage = errors.password.reduce((previousError, currentError) => previousError + "\n" + currentError);
      }

      alert("username: " + errors.username + "\n\n" +
        "Eamil: " + errors.email + "\n\n" +
        "Password: " + passwordValidatedMessage + "\n\n"
      );
    } else {
      const config = {
        method: "post",
        url: `${baseUrl}/user/create/`,
        data: data,
      };

      axios(config)
        .then((response) => {
          console.log(JSON.stringify(response.data));
          // window.location.href = `${baseUrl}/user/create/`;
        })
        .catch((error) => {
          console.log(error);
          alert("This email has been registered");
        });
    }
  };

  return (
    <Container component='main' maxWidth='xs'>
      <Paper className={classes.paper} elevation={3}>
        <img className={classes.image} src={logo} alt='images' height="40px" />
        <Typography variant="h5"><strong> Register Here </strong></Typography>
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
              type="text"
              name="username"
              placeholder="* Enter your username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
          </div>

          <div className={classes.form}>
            <TextField
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <EmailOutlinedIcon />
                  </InputAdornment>
                ),
              }}
              variant="outlined" size="medium"
              className={classes.form}
              type="email"
              name="email"
              placeholder="* Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
          </div>

          <div className={classes}>
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
              type={passwordShown ? "text" : "password"}
              name="password"
              placeholder="* Enter your password"
              value={password}
              onFocus={handlePasswordInputFocus}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          {passwordReqShow ? <div style={{ marginBottom: "0.5rem", color: "red" }}>
            <div>Password Requirements:</div>
            <div>**At least 8 characters && At least 1 digit**</div>
          </div> : null}

          <Box>
            <Button className={classes.optionBtn} onClick={ShowPassword} >Show Password</Button>
            <p>* - mandatory fields</p>
            <Grid container justifyContent='center'>
              <Button className={classes.submit} variant="outlined" size="large" onClick={handleSubmit} type="Button">
                Register
              </Button>
            </Grid>
            <Grid container justifyContent='center'>
              Already have an account? <a href="/user/login"><strong>Login</strong></a>
            </Grid>
          </Box>
        </form>
      </Paper>
    </Container >
  );
};

export default Signup;