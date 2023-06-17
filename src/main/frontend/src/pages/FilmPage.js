import {useParams} from 'react-router-dom';
import {useEffect, useState} from 'react';
import ReactPlayer from 'react-player';
import './FilmPage.css';
import {delReview, getAllReview, postReview} from "../api/server/ReviewAPI";
import {getMovieBackDropImage, getMovieDetails, getMovieTrailer} from "../api/tmdb/MovieAPI";
import WatchMovieButton from "../components/buttons/WatchMovieButton";
import styles from "./FilmsBrowsingPage.module.css";
import FavoriteMovieButton from "../components/buttons/FavoriteMovieButton";
import Userbar from "../components/navbar/Userbar";
import ToWatchMovieButton from "../components/buttons/ToWatchMovieButton";

const FilmPage = () => {
    const {id} = useParams();

    const [movie, setMovie] = useState([]);
    const [back, setBack] = useState([]);
    const [trailer, setTrailer] = useState('');
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [reviews, setReviews] = useState([]);

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleContentChange = (event) => {
        setContent(event.target.value);
    };

    const handleSubmit = async () => {
        try {
            const response = await postReview(content, movie.id, title);
        } catch (error) {
            console.log(error);
        }
    };

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
                            <img src={path + movie.poster_path} alt="Movie poster"/>
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


                <div className="review-container">
                    <h2>Leave a Review</h2>
                    <form onSubmit={handleSubmit}>
                        <input
                            type="text"
                            value={title}
                            onChange={handleTitleChange}
                            placeholder="Review Title"
                            required
                        />
                        <textarea
                            value={content}
                            onChange={handleContentChange}
                            placeholder="Write your review here"
                            required
                        ></textarea>
                        <button type="submit">Submit Review</button>
                    </form>
                    <div className="review-list">
                        <h2>All Reviews</h2>
                        {reviews.map((review) => (
                            <div className="single-review" key={review.id}>
                                <Userbar/>
                                <div className="review-content">
                                    <p>
                                        Review by <strong>{review.username}</strong>
                                    </p>
                                    <h3>{review.title}</h3>
                                    <p>{review.content}</p>
                                    <button
                                        className="delete-review-button"
                                        onClick={() => handleDelete(review.id)}>Delete Review
                                    </button>
                                </div>

                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};

export {FilmPage};
