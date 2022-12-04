import { makeStyles } from '@material-ui/core/styles'

export default makeStyles((theme) => ({
  appBar: {
    borderRadius: 15,
    margin: '30px 0',
    display: 'flex',
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
  },
  heading: {
    color: 'rgba(0,183,255, 1)',
  },
  image: {
    marginLeft: '15px',
  },
  button: {
    width: '40%',
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'center',
    color: "#ffffff",
    backgroundColor: "#38C6BD",
  },
  Search: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingLeft: '20px',
    paddingRight: '20px',
    height: '65px',
    width: "70%",
    backgroundColor: '#f5f5f5',
    borderBottom: '1px solid rgba(0, 0, 0, 0.12)',
  },
  gridContainer: {
    [theme.breakpoints.down('xs')]: {
      flexDirection: 'column-reverse',
    },
  },
  [theme.breakpoints.down('sm')]: {
    mainContainer: {
      flexDirection: "column-reverse"
    }
  },
  title: {
    textAlign: "center",
  },
  paper: {
    padding: "10px",
    borderRadius: '20px',
    margin: '10px auto'
  },
  leftSide: {
    padding: '15px',
    borderRadius: '20px',
    width:"80%",
    margin: '20px auto',
    display: 'flex',
    marginLeft: '10px'
  }
}));