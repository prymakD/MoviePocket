import {useParams} from "react-router-dom";
import {getUser} from "../api/server/UserAPI";
import {useContext, useEffect, useState} from "react";
import {getMovieDetails} from "../api/tmdb/MovieAPI";
import styles from './FilmsBrowsingPage.module.css';
import MoviePoster from "../components/poster/MoviePoster";
import WatchMovieButton from "../components/buttons/WatchMovieButton";
import FavoriteMovieButton from "../components/buttons/FavoriteMovieButton";
import ToWatchMovieButton from "../components/buttons/ToWatchMovieButton";
import "./UserFavoritePage.css";
import {UsernameContext} from "../App";

const UserToWatchPage = () => {
    const myUsername = useContext(UsernameContext);
    const {username} = useParams()

    const [favoriteList, setFavoriteList] = useState([])

    let check = false
    if (myUsername === username) {
        check = true
    }

    const getUserToWatchList = async () => {
        try {
            const response = await getUser(username);
            const movieList = response.toWatchMovie;
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
        getUserToWatchList().then()
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
                            {check
                                &&
                                <WatchMovieButton
                                    idMovie={favoriteMovie.id}
                                    className={styles.watched}
                                />
                            }
                            {check
                                &&
                                <FavoriteMovieButton
                                    idMovie={favoriteMovie.id}
                                    className={styles.favorite}
                                />
                            }
                            {check
                                &&
                                <ToWatchMovieButton
                                    idMovie={favoriteMovie.id}
                                    className={styles.toWatch}
                                />
                            }
                        </div>
                    </div>
                </div>
            ))}
        </div>
    );
}
export default UserToWatchPage;