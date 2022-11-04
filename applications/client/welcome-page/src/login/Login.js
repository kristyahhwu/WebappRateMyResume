import React from "react";
import { Button } from "@material-ui/core";

const Login = () => {

    return (
        <div className="login">
            <Button variant="outlined" size="large" color="primary">
                Login
            </Button>
            <Button variant="contained" size="large" color="primary">
                Sign Up
            </Button>
        </div>
    )

}

export default Login;