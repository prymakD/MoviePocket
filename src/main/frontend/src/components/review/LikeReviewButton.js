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
        if (!isHovered) {
            return "https://github.com/prymakD/MoviePocket/raw/c93b14bd6de8d7960d20287b6cd87ba2d3197dcd/src/main/frontend/src/images/trash_blue.png";
        } else {
            return 'https://github.com/prymakD/MoviePocket/raw/c93b14bd6de8d7960d20287b6cd87ba2d3197dcd/src/main/frontend/src/images/trash_logo.png';
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