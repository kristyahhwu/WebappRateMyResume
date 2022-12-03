import { makeStyles } from '@material-ui/core/styles';
import { deepPurple } from '@material-ui/core/colors';

/**
 * stylesheet for Navbar.js
 */

export default makeStyles((theme) => ({
    appBar: {
        borderRadius: 15,
        marginTop: '2vw',
        marginLeft: '2vw',
        width: '95vw',
        display: 'flex',
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        padding: '0px 50px',
        [theme.breakpoints.down('sm')]: {
            flexDirection: 'column',
        },
    },
    image: {
        marginLeft: '10px',
        marginTop: '5px',
    },
    toolbar: {
        display: 'flex',
        justifyContent: 'flex-end',
        width: '100%',
        [theme.breakpoints.down('sm')]: {
            width: 'auto',
        },
    },
    Search: {
        position: "absolute",
        width: '400px',
    },
    submitButton: {
        borderRadius: 5,
        display: "flex",
        gap: "15px",
        color: "#ffffff",
        backgroundColor: "#38C6BD",
        fontSize: "15px",
        fontWeight: "200",
        marginBottom: "5px",
        marginTop: "5px",
        contrastText: "#FFFFFF",
        marginLeft: '10px',
        '&:hover': {
            backgroundColor: "#006080",
        },
    },
}));