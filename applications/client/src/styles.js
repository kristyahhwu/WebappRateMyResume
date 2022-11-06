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
    width: '200px',
    display: 'flex',
    flexDirection: 'row',
    justifyContent: 'space-between',
  },
  Search: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingLeft: '20px',
    paddingRight: '20px',
    height: '65px',
    backgroundColor: '#f5f5f5',
    borderBottom: '1px solid rgba(0, 0, 0, 0.12)',
  },
  [theme.breakpoints.down('sm')] : {
    mainContainer: {
      flexDirection: "column-reverse"
    }
  }
}));