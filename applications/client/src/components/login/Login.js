import React from "react";
import { Form, Container, Row, Col } from "react-bootstrap";
import styles from "./index.module.css";
import axios from "axios";
import { Typography } from "@material-ui/core";

const Login = () => {
    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");

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
        <div className="container text-center">
            <Container>
                <Form className={styles.form}>
                    <Typography size="h5"> Welcome</Typography>
                    <Col lg={4}>
                        <Row>
                            <div className={styles.formInputComponent}>
                                <label className={styles.formLabel}>Username:</label>
                                <input
                                    className={styles.formInput}
                                    type="username"
                                    placeholder="Enter your username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                />
                            </div>
                        </Row>

                        <Row>
                            <div className={styles.formInputComponent}>
                                <label className={styles.formLabel}>Password:</label>
                                <input
                                    className={styles.formInput}
                                    type="password"
                                    name="password"
                                    placeholder="Enter your password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                />
                            </div>
                        </Row>
                    </Col>



                    <Col>
                        <Row>
                            <button className={styles.formInputBtn} onClick={handleSubmit} type="button">
                                Log In
                            </button>
                        </Row>
                    </Col>
                    <Col>
                        <label>Don't have an account? Register <a href="/user/create/">Here</a></label>
                    </Col>
                </Form>
            </Container>

            {/* <Button variant="outlined" size="large" color="primary">
                Login
            </Button>
            <Button variant="contained" size="large" color="primary">
                Sign Up
            </Button> */}
        </div >
    )

}

export default Login;