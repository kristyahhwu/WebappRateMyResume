import React from "react";
import { Row, Col, Container, Form, Button } from "react-bootstrap";
import validate from "./validate";
import styles from "./index.module.css";

const Signup = () => {
    const [password, setPassword] = React.useState("");
    const [username, setUsername] = React.useState("");
    const [email, setSemail] = React.useState("");

    const handleSubmit = (e) => {
        console.log(username);
        console.log(password);
        const data = {
            username: username,
            email: email,
            password: password,
        };

        let errors = {

        };
        errors = validate(data);
        console.log("errors..........: ", errors);

        return (
            <Container>
                <Row>
                    <Col></Col>
                    <Col md="auto">
                        <div className={styles.title}>Welcome to RateMyProfessor </div>
                    </Col>
                    <Col></Col>
                </Row>
                <Col></Col>

                <Row>
                    <Col></Col>

                    <Col lg={7}>
                        <Form className={styles.form} onSubmit={(e) => handleSubmit(e)}>
                            <Row>
                                <div className={styles.formInputComponent}>
                                    <label className={styles.formLabel}>Username*:</label>
                                    <input
                                        className={styles.formInput}
                                        type="text"
                                        name="username"
                                        placeholder="Enter your username"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                    />
                                    {errors.username && (errors.username).map((error) => <p>{error}</p>)}
                                </div>
                            </Row>

                            <Row>
                                <div className={styles.formInputComponent}>
                                    <label className={styles.formLable}>Email*:</label>
                                    <input
                                        className={styles.formInput}
                                        type="email"
                                        name="email"
                                        placeholder="Enter your email"
                                        value={email}
                                        onChange={(e) => setSemail(e.target.value)}
                                    />
                                    {errors.email && (errors.email).map((error) => <p>{error}</p>)}
                                </div>
                            </Row>

                            <Row>
                                <div className={styles.formInputComponent}>
                                    <label className={styles.formLable}>Password*():</label>
                                    <input
                                        className={styles.formInput}
                                        type="text"
                                        name="password"
                                        placeholder="Enter your password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                    {errors.password && (errors.password).map((error) => <p>{error}</p>)}
                                </div>
                            </Row>

                            <Row>
                                <Col>
                                    <Row>
                                        <Col lg={8}>
                                            <Button className={styles.formInputBtn} type="submit" onClick={handleSubmit}>
                                                Signup
                                            </Button>
                                        </Col>
                                    </Row>
                                </Col>

                            </Row>

                            <Row>
                                <span className={styles.formInputLogin}>
                                    Already have an account? <a href="/user/login">Login</a>
                                </span>
                            </Row>
                        </Form>
                    </Col>
                    <Col></Col>
                </Row>
            </Container>
        );
    };
}

export default Signup;