import { makeStyles } from '@material-ui/core/styles';

/**
 * stylesheet for Signup.js
 */

export default makeStyles((theme) => ({
    paper: {
        marginTop: theme.spacing(8),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        padding: theme.spacing(2),
        borderRadius: 10,
    },
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
        },
    },
    form: {
        width: '100%',
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(1),
    },
    submit: {
        color: "#ffffff",
        backgroundColor: "#38C6BD",
    },
    optionBtn: {
        color: "#ffffff",
        backgroundColor: "gray",
        height: '20px',
        marginTop: '-20px',
    },
    googleButton: {
        margin: theme.spacing(1, 1, 0),
        alignItems: 'center',
        display: 'flex',
        flexDirection: 'column',
    },
    image: {
        alignItems: 'center',
    }
}));