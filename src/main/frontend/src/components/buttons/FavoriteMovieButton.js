import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import styles from './FavoriteMovieButton.module.css';
import { getFavoriteMovie, postFavoriteMovie } from '../../api/server/FavoriteMovieAPI';

const FavoriteMovieButton = ({ idMovie, className }) => {
    const [favorite, setFavorite] = useState(false);

    const getFavouriteMovieState = async () => {
        try {
            const response = await getFavoriteMovie(idMovie);
            setFavorite(response);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getFavouriteMovieState();
    }, [idMovie]);

    const handleClick = async () => {
        setFavorite(!favorite)
        try {
            const response = await postFavoriteMovie(idMovie);
        } catch (error) {
            console.log(error);
        }
    };

    const getFavoriteImage = () => {
        if (favorite) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/47a2df3cfca6c558e3bbd9acc2eeb720923b4d85/src/main/frontend/src/images/liketrue.png';
        } else {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/47a2df3cfca6c558e3bbd9acc2eeb720923b4d85/src/main/frontend/src/images/likefalse.png';
        }
    };

    return (
        <img
            src={getFavoriteImage()}
            className={`${styles.default} ${className}`}
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