import {useContext, useEffect, useState} from "react";
import {getReviewLike, postReviewLike} from "../../api/server/ReviewAPI";
import styles from "./LikeReviewButton.module.css";
import PropTypes from "prop-types";
import {AuthContext} from "../../App";

const LikeReviewButton = ({idReview, className}) => {
    const isLoggedIn = useContext(AuthContext);
    const [like, setLike] = useState(false);
    const [isHovered, setIsHovered] = useState(false);

    const getLikeState = async () => {
        try {
            const response = await getReviewLike(idReview);
            setLike(response);
        } catch (error) {
            console.log(error);
        }
    };

    const handleClick = async () => {
        if (isLoggedIn) {
            setLike(!like)
            try {
                await postReviewLike(idReview);
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
        if (isHovered) {
            return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/like_blue.png';
        } else if (like) {
            return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/like_logo.png';
        } else {
            return 'https://github.com/prymakD/MoviePocket/raw/4b60404ce52704d1c756c33139a4c58817bb4f6c/src/main/frontend/src/images/like_yellow.png';
        }
    };

    useEffect(() => {
        getLikeState().then()
    }, [idReview]);

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
};

export default LikeReviewButton;