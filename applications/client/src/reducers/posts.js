
const posts = (posts = [], action) => {
    switch (action.type) {
        case 'FETCH_ALL':
            return [...posts, action.payload];
        case 'CREATE':
            return posts;
        default:
            return posts;
    }
}

export default posts;