import React, {useEffect, useState} from "react";
import PropTypes from "prop-types";
import {Link} from "react-router-dom";
import styles from "./MoviePoster.module.css";
import {getMovieRating} from "../../api/server/RatingMovieAPI";

const MoviePoster = ({ movie, className, responsible }) => {
    const [rating, setRating] = useState();
    const path_db = "https://www.themoviedb.org/t/p/w220_and_h330_face";
    const path = path_db + movie.poster_path;


    const getRatingMovie = async () => {
        try {
            const response = await getMovieRating(movie.id);
            setRating(response);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getRatingMovie().then()
    }, []);

    return (
        <div>
            {responsible && (
                <Link to={`/film/${movie.id}`}>
                    <div style={{position: 'relative', display: 'inline-block'}}>
                        <img
                            src={path}
                            className={!className ? styles.default : className}
                            alt="movie-poster"
                        />
                        <div className={styles.rating} style={{position: 'absolute', bottom: '0', right: '0'}}>
                            {rating}
                        </div>
                    </div>
                </Link>
            )}
            {!responsible && (
                <div style={{position: 'relative', display: 'inline-block'}}>
                    <img
                        src={path}
                        className={!className ? styles.default : className}
                        alt="movie-poster"
                    />
                    <div className={styles.rating} style={{position: 'absolute', bottom: '0', right: '0'}}>
                        {rating}
                    </div>
                </div>
            )}
        </div>


    )
}

MoviePoster.propTypes = {
    movie: PropTypes.object.isRequired,
    className: PropTypes.string,
    responsible: PropTypes.bool
};

export default MoviePoster;
