import React, {useContext, useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import styles from './FavoriteMovieButton.module.css';
import {getDislikedMovie, postDislikedMovie} from '../../api/server/DislikeMovieAPI';
import {AuthContext} from "../../App";

const DislikedMovieButton = ({idMovie, className}) => {
    const isLoggedIn = useContext(AuthContext);
    const [favorite, setFavorite] = useState(false);

    const getDislikedMovieState = async () => {
        try {
            const response = await getDislikedMovie(idMovie);
            setFavorite(response);
        } catch (error) {
            console.log(error);
        }
    };

    const handleClick = async () => {
        if (isLoggedIn) {
            setFavorite(!favorite)
            try {
                await postDislikedMovie(idMovie);
            } catch (error) {
                console.log(error);
            }
        } else {
            window.location.href = '/login'
        }
    };

    const getFavoriteImage = () => {
        if (favorite) {
            return 'https://github.com/prymakD/MoviePocket/raw/86fc32b132680cdfd4cf3ab0d7ffbb7667e57cdf/src/main/frontend/src/images/dislike_blue.png';
        } else {
            return 'https://github.com/prymakD/MoviePocket/raw/86fc32b132680cdfd4cf3ab0d7ffbb7667e57cdf/src/main/frontend/src/images/dislike_logo.png';
        }
    };

    useEffect(() => {
        getDislikedMovie().then()
    }, [idMovie]);

    return (
        <img
            src={getFavoriteImage()}
            className={!className ? styles.default : className}
            alt="favorite"
            onClick={handleClick}
        />
    );
};

DislikedMovieButton.propTypes = {
    idMovie: PropTypes.number.isRequired,
    className: PropTypes.string,
};

export default DislikedMovieButton;