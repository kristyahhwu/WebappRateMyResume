/**
 * function to validate if user inputs are valid
 * @param {string} values input value
 * @returns error message if any
 */

const Validate = (values) => {
    let errors = {
        username: [],
        email: [],
        password: [],
    };

    const atLeastOneNum = new RegExp("^(?=.*?[0-9]).+$");

    // #1 verify username
    if (!values.username.trim()) {
        errors.username.push("Username Required!");
    }

    // #2 verify email
    if (!values.email) {
        errors.email.push(" Email required");
    } else if (!/\S+@\S+\.\S+/.test(values.email)) {
        errors.email.push("Email address is invalid!");
    }

    // #3 verify password
    if (!values.password) {
        errors.password.push("Password required!");
    } else {

        if (!atLeastOneNum.test(values.password)) {
            errors.password.push("at least 1 number! ");
        }

        if (values.password.length < 8) {
            errors.password.push("Min 8 characters! ");
        }

    }
    return errors;

}
export default Validate;
