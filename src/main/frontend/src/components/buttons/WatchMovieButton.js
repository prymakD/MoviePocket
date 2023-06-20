import React, {useContext, useEffect, useState} from 'react';
import PropTypes from 'prop-types';
import styles from './WatchMovieButton.module.css';
import {getWatchedMovie, postWatchedMovie} from "../../api/server/WatchedMovieAPI";
import {AuthContext} from "../../App";

const WatchMovieButton = ({ idMovie, className }) => {
    const [watched, setWatched] = useState(false);
    const [isHovered, setIsHovered] = useState(false);
    const [isClicked, setIsClicked] = useState(false);
    const isLoggedIn = useContext(AuthContext);

    const getWatchedMovieState = async () => {
        try {
            const response = await getWatchedMovie(idMovie);
            setWatched(response);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getWatchedMovieState().then()
    }, [idMovie]);

    const handleClick = async () => {
        if (isLoggedIn){
            setWatched(!watched)
            setIsClicked(true);
            try {
                await postWatchedMovie(idMovie);
            } catch (error) {
                console.log(error);
            }
        } else {
            window.location.href = '/login'
        }
    }

    const handleMouseEnter = () => {
        setIsHovered(true);
    };

    const handleMouseLeave = () => {
        setIsHovered(false);
        setIsClicked(false);
    };

    const getWatchedImage = () => {
        if (isHovered && watched && !isClicked) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/949a92f2aa9d518691eab7f1cad71b60654de0e6/src/main/frontend/src/images/eyeRed.png';
        } else if (isHovered && watched && isClicked) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/949a92f2aa9d518691eab7f1cad71b60654de0e6/src/main/frontend/src/images/eyeGreen.png';
        } else if (watched) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/949a92f2aa9d518691eab7f1cad71b60654de0e6/src/main/frontend/src/images/eyeGreen.png';
        }   else {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/949a92f2aa9d518691eab7f1cad71b60654de0e6/src/main/frontend/src/images/eyeLogo.png';
        }
    };

    return (
        <img
            src={getWatchedImage()}
            className={!className ? styles.default : className}
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