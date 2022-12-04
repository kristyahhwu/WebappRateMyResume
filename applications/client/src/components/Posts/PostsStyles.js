import { makeStyles } from '@material-ui/core/styles';

/**
 * stylesheet for Posts.js
 */

export default makeStyles((theme) => ({
  mainContainer: {
    borderRadius: 15,
    width: '81vw',
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: '10px',
  },
  smMargin: {
    margin: theme.spacing(1),
  },
  actionDiv: {
    textAlign: 'center',
  },
}));