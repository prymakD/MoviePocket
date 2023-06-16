import React, {useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import styles from './WatchMovieButton.module.css';
import { getWatchedMovie, postWatchedMovie } from "../../api/server/WatchedMovieAPI";

const WatchMovieButton = ({ idMovie, className }) => {
    const [watched, setWatched] = useState(false);
    const [isHovered, setIsHovered] = useState(false);
    const [isClicked, setIsClicked] = useState(false);

    const getWatchedMovieState = async () => {
        try {
            const response = await getWatchedMovie(idMovie);
            setWatched(response);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getWatchedMovieState();
    }, [idMovie]);

    const handleClick = async () => {
        setWatched(!watched)
        setIsClicked(true);
        try {
            const response = await postWatchedMovie(idMovie);
        } catch (error) {
            console.log(error);
        }
    };

    const handleMouseEnter = () => {
        setIsHovered(true);
    };

    const handleMouseLeave = () => {
        setIsHovered(false);
        setIsClicked(false);
    };

    const getWatchedImage = () => {
        if (isHovered && watched && !isClicked) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/47a2df3cfca6c558e3bbd9acc2eeb720923b4d85/src/main/frontend/src/images/eye_red.png';
        } else if (isHovered && watched && isClicked) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/47a2df3cfca6c558e3bbd9acc2eeb720923b4d85/src/main/frontend/src/images/eye_green.png';
        } else if (watched) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/47a2df3cfca6c558e3bbd9acc2eeb720923b4d85/src/main/frontend/src/images/eye_green.png';
        }   else {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/47a2df3cfca6c558e3bbd9acc2eeb720923b4d85/src/main/frontend/src/images/eye.png';
        }
    };

    return (
        <img
            src={getWatchedImage()}
            className={`${styles.default} ${className}`}
            alt="watched"
            onClick={handleClick}
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
        />
    );
};

WatchMovieButton.propTypes = {
    idMovie: PropTypes.number.isRequired,
    className: PropTypes.string,
};

export default WatchMovieButton;