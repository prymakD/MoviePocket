import {useParams} from 'react-router-dom';
import {useContext, useEffect, useState} from 'react';
import ReactPlayer from 'react-player';
import './FilmPage.css';
import {delReview, getAllReview} from "../api/server/ReviewAPI";
import {getMovieBackDropImage, getMovieDetails, getMovieTrailer} from "../api/tmdb/MovieAPI";
import WatchMovieButton from "../components/buttons/WatchMovieButton";
import styles from "./FilmPage.module.css";
import FavoriteMovieButton from "../components/buttons/FavoriteMovieButton";
import Userbar from "../components/navbar/Userbar";
import ToWatchMovieButton from "../components/buttons/ToWatchMovieButton";
import MoviePoster from "../components/poster/MoviePoster";
import CreateReviewForm from "../components/review/CreateReviewForm";
import {AuthContext} from "../App";

const FilmPage = () => {
    const {id} = useParams();

    const [movie, setMovie] = useState([]);
    const [back, setBack] = useState([]);
    const [trailer, setTrailer] = useState('');
    const [reviews, setReviews] = useState([]);
    const isLoggedIn = useContext(AuthContext);

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';

    const handleDelete = async (idReview) => {
        try {
            const response = await delReview(idReview);
            await getReviews();
        } catch (error) {
            console.log(error);
        }
    };

    const getMovie = async () => {
        try {
            const response = await getMovieDetails(id);
            setMovie(response);
        } catch (error) {
            console.log(error);
        }
    };

    const getBack = async () => {
        try {
            const response = await getMovieBackDropImage(id);
            console.log(response);
            setBack(response);
        } catch (error) {
            console.log(error);
        }
    };

    const getTrailer = async () => {
        try {
            const response = await getMovieTrailer(id);
            setTrailer(response);
        } catch (error) {
            console.log(error);
        }
    };

    const getReviews = async () => {
        try {
            const response = await getAllReview(id);
            if (response) setReviews(response);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getMovie().then();
        getBack().then();
        getTrailer().then();
        getReviews().then();
    }, []);

    return (
        <div className="film-info-container">
            <div>
                <div
                    className="film-header"
                    style={{backgroundImage: `url(${path + back})`}}
                ></div>
                <div className="film-media-container">
                    <div className="movie-like-contaner">
                        <div className="film-poster">
                            <MoviePoster
                            movie={movie}/>
                        </div>
                        <div className="like-container">
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
                                className={styles.favorite}
                            />
                        </div>
                    </div>
                    <div className="film-details">
                        <h1>{movie.title}</h1>
                        <p>Release Date: {movie.release_date}</p>
                        <p>Runtime: {movie.runtime} minutes</p>
                        {movie.genres && movie.genres.length > 0 && (
                            <p>Genres: {movie.genres.map((genre) => genre.name).join(", ")}</p>
                        )}
                        <p>Revenue: {movie.revenue} $</p>
                        <p>Overview: {movie.overview}</p>
                    </div>
                </div>

                <div className="player-wrapper">
                    {trailer && (
                        <ReactPlayer
                            className="react-player"
                            url={trailer}
                            controls={true}
                        />
                    )}
                </div>
                {isLoggedIn
                    &&
                    <CreateReviewForm movieId={movie.id} updateReviews={getReviews()} />
                }
                <div className="review-list">
                    <h2 color="#F1B36E">All Reviews</h2>
                    {reviews.map((review) => (
                        <div className="single-review" key={review.id}>
                            <Userbar/>
                            <div className="review-content">
                                <h>
                                    Review by <strong className="logo-text">{review.username}</strong>
                                    <p className="blue-text">Created: <span
                                        className="yellow-text">{review.dataCreated ? new Date(review.dataCreated).toLocaleDateString() : '0'}</span>
                                    </p>
                                </h>
                                <h3>{review.title}</h3>
                                <p>{review.content}</p>
                            </div>
                            <div className='delete-review-button'>
                                <img
                                    src="https://github.com/prymakD/MoviePocket/raw/c93b14bd6de8d7960d20287b6cd87ba2d3197dcd/src/main/frontend/src/images/trash_blue.png"
                                    alt="USER"
                                    onClick={() => handleDelete(review.id)}
                                />
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export {FilmPage};
