import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";
import {getRandomMovie} from "../api/tmdb/MovieAPI";
import {getUser} from "../api/server/UserAPI";

const UserWatchedPage = () => {
    const {username} = useParams();

    const [watchedList, setWatchedList] = useState([])
    const [backgroundImage, setBackgroundImage] = useState('');

    const getRandomMovieImage = async () => {
        try {
            const response = await getRandomMovie();
            setBackgroundImage(`https://image.tmdb.org/t/p/original${response.backdrop_path}`);
        } catch (error) {
            console.log(error);
        }
    };
    const getUserWatchedList = async () => {
        try {
            const response = await getUser(username);
            setWatchedList(response.watchedMovie);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getUserWatchedList().then()
        getRandomMovieImage().then()
    }, [username])

    return (
        <div
            style={{backgroundImage: `url(${backgroundImage})`}}>
            <h1>Favorite Movies of {username}</h1>
            <ul>
                {watchedList.map((movieId) => (
                    <li key={movieId}>{movieId}</li>
                ))}
            </ul>
        </div>
    );
}
export default UserWatchedPage;