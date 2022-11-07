import { useEffect, useState } from "react";
import { useParams } from "react-router";

let editMode = false;


const PostDetail = () => {
    const { id } = useParams();
    const [metadata, setMetadata] = useState(null);
    const [newMetadata, setNewMetadata] = useState(null);

    // useEffect(() => {
    //     getMetadata(db, id)
    //         .then((res) => setMetadata(res));
    // }, [startEdit])

    console.log(metadata)

    return (
        <div className="imgDiv">
            { id }

        </div>
    );
}

export default PostDetail;