import React from "react";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";
import styles from "./MoviePoster.module.css";

const MoviePoster = ({ movie, className, responsible }) => {
    const path_db = "https://www.themoviedb.org/t/p/w220_and_h330_face";
    const path = path_db + movie.poster_path;

    if (responsible) {
        return (
            <Link to={`/film/${movie.id}`}>
                <img
                    src={path}
                    className={!className ? styles.default : className}
                    alt="movie-poster"
                />
            </Link>
        );
    } else {
        return (
            <img
                src={path}
                className={!className ? styles.default : className}
                alt="movie-poster"
            />
        );
    }
};

MoviePoster.propTypes = {
    movie: PropTypes.object.isRequired,
    className: PropTypes.string,
    responsible: PropTypes.bool
};

export default MoviePoster;
