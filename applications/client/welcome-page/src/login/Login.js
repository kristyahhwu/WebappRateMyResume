import React from "react";
import { Button } from '@material-ui/core';
import { Form, Container, Row, Col } from "react-bootstrap";
import styles from "./index.module.css";

const Login = () => {

    return (
        <div className="login">
            <Container>
                <Row>
                    <Col></Col>
                    <Col lg={4}>
                        <Form className={styles.form}>
                            <Row>
                                <div className={styles.formInputComponent}>
                                    <label className={styles.formLable}>Email:</label>
                                    <input
                                        className={styles.formInput}
                                        type="email"
                                        placeholder="Enter your email"
                                    />
                                </div>
                            </Row>
                            <Row>
                                <div className={styles.formInputComponent}>
                                    <label className={styles.formLable}>Password:</label>
                                    <input
                                        className={styles.formInput}
                                        type="password"
                                        name="password"
                                        placeholder="Enter your password"
                                    />
                                </div>
                            </Row>

                            <Row>
                                <Col></Col>
                                <Col>
                                    <Button variant="outlined" type="submit">
                                        Log In
                                    </Button>
                                </Col>
                                <Col></Col>
                            </Row>

                        </Form>
                    </Col>
                    <Col></Col>
                </Row>
            </Container>

            {/* <Button variant="outlined" size="large" color="primary">
                Login
            </Button>
            <Button variant="contained" size="large" color="primary">
                Sign Up
            </Button> */}
        </div>
    )

}

export default Login;