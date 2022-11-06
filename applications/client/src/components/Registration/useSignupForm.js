import { useState } from 'react';

const useSignupForm = validate => {
    const [values, setValues] = useState({
        username: '',
        email: '',
        password: '',
    });

    const [errors, setErrors] = useState({
        username: [],
        email: [],
        password: [],
    });


    const handleChange = e => {
        const { username, value } = e.target;
        setValues({
            ...values,
            [username]: value
        });
    };

    const handleSignup = e => {
        e.preventDefault();
        setErrors(validate(values));
    };

    return { handleChange, handleSignup, values, errors };
};

export default useSignupForm;