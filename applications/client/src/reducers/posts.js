import { FETCH_ALL, CREATE, LIKE } from '../constants/actionTypes';

const posts = (posts = [], action) => {
    switch (action.type) {
        case LIKE:
            return posts.map((post) => (post._id === action.payload._id ? action.payload : post));
        case FETCH_ALL:
            return action.payload;
        case CREATE:
            return [...posts, action.payload];
        default:
            return posts;
    }
}

export default posts;