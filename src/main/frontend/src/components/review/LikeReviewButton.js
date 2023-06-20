import React, {useContext, useEffect, useState} from "react";
import {getReviewLike, postReviewLike} from "../../api/server/ReviewAPI";
import styles from "./LikeReviewButton.module.css";
import PropTypes from "prop-types";
import {AuthContext} from "../../App";

const LikeReviewButton = ({idReview, className, up}) => {
    const isLoggedIn = useContext(AuthContext);
    const [isHovered, setIsHovered] = useState(false);
    const [like, setLike] = useState();

    const getLikeState = async () => {
        try {
            const response = await getReviewLike(idReview);
            setLike(response);
            console.log(response);
        } catch (error) {
            console.log(error);
        }
    };

    const handleClick = async () => {
        if (isLoggedIn) {
            try {
                setLike(!like);
                if (up === false && like === undefined){
                    setLike(false)
                }
                await postReviewLike(idReview, up);
            } catch (error) {
                console.log(error);
            }
        } else {
            window.location.href = '/login'
        }
    };

    const handleMouseEnter = () => {
        setIsHovered(true);
    };

    const handleMouseLeave = () => {
        setIsHovered(false);
    };

    const getLikeImage = () => {
        if (up) {
            if (isHovered) {
                return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/like_blue.png';
            } else if (like === true) {
                return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/like_logo.png';
            } else {
                return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/like_yellow.png';
            }
        } else {
            if (isHovered) {
                return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/dislike_blue.png';
            } else if (like === false) {
                return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/dislike_logo.png';
            } else {
                return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/dislike_yellow.png';
            }
        }
    };

    useEffect(() => {
        getLikeState().then()
    }, [like]);


    return (
        <img
            src={getLikeImage()}
            className={!className ? styles.default : className}
            alt="like"
            onClick={handleClick}
            onMouseEnter={handleMouseEnter}
            onMouseLeave={handleMouseLeave}
        />
    )
}

LikeReviewButton.propTypes = {
    idReview: PropTypes.number.isRequired,
    className: PropTypes.string,
    up: PropTypes.bool.isRequired
};

export default LikeReviewButton;