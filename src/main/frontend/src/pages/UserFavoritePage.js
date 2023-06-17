import {useParams} from "react-router-dom";
import {getUser} from "../api/server/UserAPI";
import {useEffect, useState} from "react";
import {getRandomMovie} from "../api/tmdb/MovieAPI";

const UserFavoritePage = () => {
    const {username} = useParams();

    const [favoriteList, setFavoriteList] = useState([])
    const [backgroundImage, setBackgroundImage] = useState('');

    const getRandomMovieImage = async () => {
        try {
            const response = await getRandomMovie();
            setBackgroundImage(`https://image.tmdb.org/t/p/original${response.backdrop_path}`);
        } catch (error) {
            console.log(error);
        }
    };
    const getUserFavoriteList = async () => {
        try {
            const response = await getUser(username);
            setFavoriteList(response.likeMovie);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getUserFavoriteList().then()
        getRandomMovieImage().then()
    }, [username])

    return (
        <div
            style={{backgroundImage: `url(${backgroundImage})`}}>
            <h1>Favorite Movies of {username}</h1>
            <ul>
                {favoriteList.map((movieId) => (
                    <li key={movieId}>{movieId}</li>
                ))}
            </ul>
        </div>
    );
}
export default UserFavoritePage;