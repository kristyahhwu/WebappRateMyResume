import { makeStyles } from '@material-ui/core/styles';

/**
 * stylesheet for Posts.js
 */

export default makeStyles((theme) => ({
  mainContainer: {
    display: "flex",
    justifyContent: "space-between",
    marginTop: "10px",
    alignItems: "stretch",
  },
  smMargin: {
    margin: theme.spacing(1),
  },
  actionDiv: {
    textAlign: 'center',
  },
}));