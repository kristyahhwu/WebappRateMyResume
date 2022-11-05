import React from "react";
import { Row, Col, Container } from "react-bootstrap";
import validate from "./validate";
import styles from "./Signup.module.css";
import axios from "axios";

const FormSignup = () => {
    const [username, setUsername] = React.useState("");
    const [password, setPassword] = React.useState("");
    const [email, setSemail] = React.useState("");
    const [passwordShown, setPasswordShown] = React.useState(false); // dont show password at first
    const [passwordReqShow, setPasswordReqShow] = React.useState(false);

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
        console.log(username);
        console.log(password);
        let data = {
            username: username,
            email: email,
            password: password,
        };


        errors = validate(data);
        console.log("errors..........: ", errors);


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
                url: "/user/create/",
                // url: "http://34.94.186.97:3000/user/create/",
                data: data,
            };

            axios(config)
                .then((response) => {
                    console.log(JSON.stringify(response.data));
                })
                .catch((error) => {
                    console.log(error);
                    alert("This email has been registered");
                });
        }
    };

    return (
        <Container>
            <div className={styles.registerComponent}>
                <Row>
                    <Col lg={7}>
                        <form className={styles.form} onSubmit={(e) => handleSubmit(e)}>
                            <Row>
                                <div className={styles.formInputs}>
                                    <label className={styles.formLabel}>Username:</label>
                                    <input
                                        className={styles.formInput}
                                        type="text"
                                        name="username"
                                        placeholder="Enter your first name"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                    />
                                </div>
                            </Row>

                            <Row>
                                <div className={styles.formInputs}>
                                    <label className={styles.formLabel}>Email*:</label>
                                    <input
                                        className={styles.formInput}
                                        type="email"
                                        name="email"
                                        placeholder="Enter your email"
                                        value={email}
                                        onChange={(e) => setSemail(e.target.value)}
                                    />
                                </div>
                            </Row>

                            <Row>
                                <div className={styles.formInputs}>
                                    <label className={styles.formLabel}>Password*:</label>
                                    <input
                                        className={styles.formInput}
                                        type={passwordShown ? "text" : "password"}
                                        name="password"
                                        placeholder="Enter your password"
                                        value={password}
                                        onFocus={handlePasswordInputFocus}
                                        onChange={(e) => setPassword(e.target.value)}
                                    />
                                </div>

                                {passwordReqShow ? <div style={{ maginTop: "1rem", marginBottom: "1rem", color: "red" }}><Row><div>Password Requirements:</div></Row>
                                    <Row><div>at least 8 characters</div></Row>
                                    <Row><div>at least 1 digit</div></Row>
                                </div> : null}
                            </Row>

                            <Row>
                                <button className={styles.showPasswordBtn} onClick={ShowPassword} >Show Password</button>
                            </Row>

                            <Col lg={8}>
                                <Row>
                                    <Col>
                                        <p>* - mandatory fields</p>
                                    </Col>
                                </Row>
                            </Col>
                            <Row></Row>

                            <Row>
                                <Col></Col>
                                <Col>
                                    <button className={styles.formInputBtn} onClick={handleSubmit} type="button">
                                        Register
                                    </button>
                                </Col>

                                <Col></Col>
                            </Row>

                            <Row>
                                <span className={styles.formInputLogin}>
                                    Already have an account? <a href="/user/login">Login</a>
                                </span>
                            </Row>
                        </form>
                    </Col>
                    <Col></Col>
                </Row>
            </div>
        </Container>
    );
};

export default FormSignup;