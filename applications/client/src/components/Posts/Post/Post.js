import React from 'react';
import { Card, CardActions, CardContent, CardMedia, Button, Typography } from '@material-ui/core';
import ThumbUpAltIcon from '@material-ui/icons/ThumbUpAlt';
import MoreHorizIcon from '@material-ui/icons/MoreHoriz';
import moment from 'moment';
import useStyles from './PostStyles';
import { useDispatch } from 'react-redux';
import { Link } from "react-router-dom";

import { likePost } from '../../../actions/posts';

const Post = ({ post }) => {
  const classes = useStyles();
  const dispatch = useDispatch();

  console.log(post)
  if (post.length === 0) {
    return <></>
  }
  return (
    <Card className={classes.card}>
      <CardMedia className={classes.media} image={post.selectedFile} title={post.title} />
      <div className={classes.overlay}>
        <Typography variant="h6">{post.creator}</Typography>
        <Typography variant="body2">{moment(post.postDate).fromNow()}</Typography>
      </div>
      <div className={classes.overlay2}>
        <Button color="white" size="small" onClick={() => { }}>
          <MoreHorizIcon fontSize="medium" />
        </Button>
      </div>

      <Typography className={classes.title} variant="h5" gutterBottom>
        <Link to={`/post/${post.postId}`}>{post.title}</Link>
      </Typography>
      <CardContent>
        <Typography variant="body2" color="textSecondary" component="p">{post.message}</Typography>
      </CardContent>
      <CardActions className={classes.cardActions}>
        <Button size="small" color="primary" onClick={() => dispatch(likePost(post._id))}>
          <ThumbUpAltIcon fontSize="small" />
          &nbsp; Like &nbsp;
          {post.likeCount}
        </Button>
      </CardActions>
    </Card>
  );
}

export default Post;