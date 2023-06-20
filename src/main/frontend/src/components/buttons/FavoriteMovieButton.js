import React, {useContext, useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import styles from './FavoriteMovieButton.module.css';
import {getFavoriteMovie, postFavoriteMovie} from '../../api/server/FavoriteMovieAPI';
import {AuthContext} from "../../App";

const FavoriteMovieButton = ({ idMovie, className }) => {
    const isLoggedIn = useContext(AuthContext);
    const [favorite, setFavorite] = useState(false);

    const getFavouriteMovieState = async () => {
        try {
            const response = await getFavoriteMovie(idMovie);
            setFavorite(response);
        } catch (error) {
            console.log(error);
        }
    };

    const handleClick = async () => {
        if (isLoggedIn) {
            setFavorite(!favorite)
            try {
                await postFavoriteMovie(idMovie);
            } catch (error) {
                console.log(error);
            }
        } else {
            window.location.href = '/login'
        }
    };

    const getFavoriteImage = () => {
        if (favorite) {
            return 'https://github.com/prymakD/MoviePocket/raw/220c79cf7b130a70dff2ad6423b52db5d99650b5/src/main/frontend/src/images/likeTrue.png';
        } else {
            return 'https://github.com/prymakD/MoviePocket/raw/220c79cf7b130a70dff2ad6423b52db5d99650b5/src/main/frontend/src/images/likeFalse.png';
        }
    };

    useEffect(() => {
        getFavouriteMovieState().then()
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

FavoriteMovieButton.propTypes = {
    idMovie: PropTypes.number.isRequired,
    className: PropTypes.string,
};

export default FavoriteMovieButton;