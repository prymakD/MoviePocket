import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getUser} from "../api/server/UserAPI";
import {getRandomMovie} from "../api/tmdb/MovieAPI";
import './UserPage.css';


const UserPage = () => {
    const {username} = useParams();

    const [user, setUser] = useState();
    const [backgroundImage, setBackgroundImage] = useState('');

    const getUserInfo = async () => {
        try {
            const response = await getUser(username);
            setUser(response);
        } catch (error) {
            console.log(error);
        }
    };

    const getRandomMovieImage = async () => {
        try {
            const response = await getRandomMovie();
            setBackgroundImage(`https://image.tmdb.org/t/p/original${response.backdrop_path}`);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getUserInfo().then()
        getRandomMovieImage().then()
    }, [])

    if (!user) {
        // Display loading state or a spinner while fetching user data
        return <div>Loading...</div>;
    }

    return (
        <div className="user-page-container"
             style={{backgroundImage: `url(${backgroundImage})`}}>
            <div className="user-info-container">
                <div className="text-info-container">
                    <div className="username-container">
                        <h1>{user.username}</h1>
                    </div>
                    <p className="blue-text">Created: <span
                        className="yellow-text">{new Date(user.created).toLocaleDateString()}</span></p>
                    <p className="blue-text">Bio: <span className="yellow-text">{user.bio}</span></p>
                    <p className="blue-text">Total Liked Movies: <span
                        className="yellow-text">{user.likeMovie.length}</span></p>
                    <p className="blue-text">Total Watched Movies: <span
                        className="yellow-text">{user.watchedMovie.length}</span></p>
                    <p className="blue-text">Total Disliked Movies: <span
                        className="yellow-text">{user.dislikeMovie.length}</span></p>
                    <p className="blue-text">Total Rating: <span
                        className="yellow-text">{user.ratingMovie.length}</span>
                    </p>
                    <p className="blue-text">Total Reviews: <span
                        className="yellow-text">{user.reviewList.length}</span>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default UserPage;