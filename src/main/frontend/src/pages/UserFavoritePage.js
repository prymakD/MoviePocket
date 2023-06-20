import {useParams} from "react-router-dom";
import {getUser} from "../api/server/UserAPI";
import {useEffect, useState} from "react";
import {getMovieDetails} from "../api/tmdb/MovieAPI";
import styles from './FilmsBrowsingPage.module.css';
import MoviePoster from "../components/poster/MoviePoster";
import WatchMovieButton from "../components/buttons/WatchMovieButton";
import FavoriteMovieButton from "../components/buttons/FavoriteMovieButton";
import ToWatchMovieButton from "../components/buttons/ToWatchMovieButton";
import "./UserFavoritePage.css";

const UserFavoritePage = () => {
    const {username} = useParams();

    const [favoriteList, setFavoriteList] = useState([])

    const getUserFavoriteList = async () => {
        try {
            const response = await getUser(username);
            const movieList = response.likeMovie;
            console.log(movieList);
            const favoriteListData = await Promise.all(
                movieList.map(async (movie) => {
                    const favoriteMovie = await getMovieDetails(movie);
                    console.log(favoriteMovie);
                    return {
                        ...favoriteMovie,
                    };
                })
            )
            setFavoriteList(favoriteListData);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getUserFavoriteList().then()
    }, [username])

    return (
        <div className="films-browser-list">
            {favoriteList.map(favoriteMovie => (
                <div className="film-browser-card" key={favoriteMovie.id}>
                    <div className="film-browser-poster">
                        <MoviePoster
                            movie={favoriteMovie}
                            className={styles.browsingPoster}
                            responsible={true}/>
                        <div className="film-poster-buttons">
                            <WatchMovieButton
                                idMovie={favoriteMovie.id}
                                className={styles.watched}
                            />
                            <FavoriteMovieButton
                                idMovie={favoriteMovie.id}
                                className={styles.favorite}
                            />
                            <ToWatchMovieButton
                                idMovie={favoriteMovie.id}
                                className={styles.toWatch}
                            />
                        </div>
                    </div>
                </div>
            ))}
        </div>
    );
}
export default UserFavoritePage;