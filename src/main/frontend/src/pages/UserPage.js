import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getUser} from "../api/server/UserAPI";


const UserPage = () => {
    const {username} = useParams();

    const [user, setUser] = useState();
    const getUserInfo = async () => {
        try {
            const response = await getUser(username);
            setUser(response);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getUserInfo().then()
    }, [])

    if (!user) {
        // Display loading state or a spinner while fetching user data
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>{user.username}</h1>
            <p>Bio: {user.bio}</p>
            <p>Created: {user.created}</p>
            <h2>Liked Movies:</h2>
            <ul>
                {user.likeMovie.map((movieId) => (
                    <li key={movieId}>{movieId}</li>
                ))}
            </ul>
            <h2>Watched Movies:</h2>
            <ul>
                {user.watchedMovie.map((movieId) => (
                    <li key={movieId}>{movieId}</li>
                ))}
            </ul>
            <h2>Movie Reviews:</h2>
            <ul>
                {user.reviewList.map((review) => (
                    <li key={review.id}>
                        <h3>{review.title}</h3>
                        <p>{review.content}</p>
                        <p>Created: {review.dataCreated}</p>
                        <p>Updated: {review.dataUpdated}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default UserPage;