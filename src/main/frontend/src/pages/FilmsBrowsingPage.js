import {Link, useNavigate, useParams} from 'react-router-dom'
import {useEffect, useState} from "react";
import './FilmsBrowsingPage.css'
import WatchMovieButton from "../components/buttons/WatchMovieButton";
import FavoriteMovieButton from "../components/buttons/FavoriteMovieButton";
import styles from './FilmsBrowsingPage.module.css';
import {getFavoriteMovie} from "../api/server/FavoriteMovieAPI";
import {getMovies} from "../api/tmdb/PaginationMovieAPI";
import Pagination from "../components/pagination/Pagination";
import ToWatchMovieButton from "../components/buttons/ToWatchMovieButton";
import MoviePoster from "../components/poster/MoviePoster";

const FilmsBrowsingPage = () => {
    const {currentPage} = useParams();
    const navigate = useNavigate();

    const [movies, setMovies] = useState([]);
    const [totalPages, setTotalPages] = useState(6);

    const getMovieList = async (page) => {
        try {
            const movieList = await getMovies(page)
            const moviesWithLike = await Promise.all(
                movieList.map(async (movie) => {
                    const likedMovie = await getFavoriteMovie(movie.id);
                    return {
                        ...movie,
                        likedMovie: likedMovie
                    };
                })
            );
            setMovies(moviesWithLike);
        } catch (error) {
            console.log(error);
        }
    };

    const handlePreviousPage = () => {
        if (parseInt(currentPage) > 1) {
            navigate(`/films/${parseInt(currentPage) - 1}`);
        }
    };

    const handleNextPage = () => {
        if (parseInt(currentPage) < totalPages) {
            navigate(`/films/${parseInt(currentPage) + 1}`);
        }
    };

    useEffect(() => {
        getMovieList(currentPage).then();
    }, [currentPage]);

    return (
        <div className="films-browser-container">
            <h1 className="blue-text">Movie Browser</h1>
            <div className="films-browser-list">
                {movies.map(movie => (
                    <div className="film-browser-card" key={movie.id}>
                        <div className="film-browser-poster">
                            <MoviePoster
                                movie={movie}
                                className={styles.browsingPoster}
                                responsible={true}/>
                            <div className="film-poster-buttons">
                                <WatchMovieButton
                                    idMovie={movie.id}
                                    className={styles.watched}
                                />
                                <FavoriteMovieButton
                                    idMovie={movie.id}
                                    className={styles.favorite}
                                />
                                <ToWatchMovieButton
                                    idMovie={movie.id}
                                    className={styles.toWatch}
                                />
                            </div>
                        </div>
                        <div className="film-browser-info">
                            <div className="film-browser-title">
                                <Link to={`/film/${movie.id}`}>
                                    <p className="yellow-text">{movie.title}</p>
                                </Link>
                                <div className="release-date-button">
                                    <p style={{fontSize: '12px'}}>{movie.release_date.split('-')[0]}</p>
                                    {movie.genres && movie.genres.length > 0 && (
                                        <p style={{fontSize: '14px'}}>Genres: {movie.genres.map((genre) => genre.name).join(", ")}</p>
                                    )}

                                </div>

                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <Pagination
                currentPage={parseInt(currentPage)}
                totalPages={totalPages}
                onPreviousPage={handlePreviousPage}
                onNextPage={handleNextPage}
            />
        </div>

    )
}

export default FilmsBrowsingPage;
