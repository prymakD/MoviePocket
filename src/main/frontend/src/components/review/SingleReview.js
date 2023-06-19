import React, {useContext} from 'react';
import Userbar from "../navbar/Userbar";
import styles from "./SingleReview.module.css";
import ReviewDeleteButton from "./ReviewDeleteButton";
import {UsernameContext} from "../../App";
import PropTypes from "prop-types";
import LikeReviewButton from "./LikeReviewButton";

const SingleReview = ({ review, updateReviews, className }) => {
    const username = useContext(UsernameContext);

    return (
        <div className={!className ? styles.singleReview : className} key={review.id}>
            <Userbar/>
            <div className={!className ? styles.reviewContent : className}>
                <h>
                    Review by <strong className={!className ? styles.logoText : className}>{review.username}</strong>
                    <p className="blue-text">Created:
                        <span
                        className="yellow-text">{review.dataCreated ? new Date(review.dataCreated).toLocaleDateString() : '0'}
                        </span>
                    </p>
                </h>
                <h3>{review.title}</h3>
                <p>{review.content}</p>
            </div>
            {username === review.username
                &&
                <ReviewDeleteButton
                    idReview={review.id}
                    updateReviews={updateReviews}
                />
            }
            <LikeReviewButton
                idReview={review.id}
            />
        </div>
    );
};

SingleReview.propTypes = {
    review: PropTypes.object.isRequired,
    updateReviews: PropTypes.func.isRequired,
    className: PropTypes.string,
};

export default SingleReview;