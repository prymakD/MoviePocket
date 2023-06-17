import {Link, useParams} from "react-router-dom";
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
            <Link to={`/user/${username}/favorite`}>
                <p style={{fontSize: '18px'}}>favorite</p>
            </Link>
            <Link to={`/user/${username}/watched`}>
                <p style={{fontSize: '18px'}}>watched</p>
            </Link>
            <h1>{user.username}</h1>
            <p>Bio: {user.bio}</p>
            <p>Created: {user.crated}</p>
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
            <div className="user-info-container">
                <div className="username-container">
                    <h1>{user.username}</h1>
                </div>
                <div className="user-created">
                    <p className="blue-text">Created: </p>
                    <p className="yellow-text">{new Date(user.created).toLocaleDateString()}</p>
                </div>
                <div className="bio-container">
                    <p className="blue-text">Bio:</p>
                    <p className="yellow-text">{user.bio}</p>
                </div>
            </div>
            <p className="blue-text">Created: {user.сrated}</p>
            {/*<h2>Liked Movies:</h2>*/}
            {/*<ul>*/}
            {/*    {user.likeMovie.map((movieId) => (*/}
            {/*        <li key={movieId}>{movieId}</li>*/}
            {/*    ))}*/}
            {/*</ul>*/}
            {/*<h2>Watched Movies:</h2>*/}
            {/*<ul>*/}
            {/*    {user.watchedMovie.map((movieId) => (*/}
            {/*        <li key={movieId}>{movieId}</li>*/}
            {/*    ))}*/}
            {/*</ul>*/}
            {/*<h2>Movie Reviews:</h2>*/}
            {/*<ul>*/}
            {/*    {user.reviewList.map((review) => (*/}
            {/*        <li key={review.id}>*/}
            {/*            <h3>{review.title}</h3>*/}
            {/*            <p>{review.content}</p>*/}
            {/*            <p>Created: {review.dataCreated}</p>*/}
            {/*            <p>Updated: {review.dataUpdated}</p>*/}
            {/*        </li>*/}
            {/*    ))}*/}
            {/*</ul>*/}
        </div>
    );
}

export default UserPage;