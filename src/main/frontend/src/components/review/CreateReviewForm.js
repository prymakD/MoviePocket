import React, {useState} from 'react';
import {postReview} from "../../api/server/ReviewAPI";
import "./CreateReviewForm.css"
import PropTypes from "prop-types";


const CreateReviewForm = ({ movieId, updateReviews }) => {

    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleTitleChange = (event) => {
        setTitle(event.target.value);
    };

    const handleContentChange = (event) => {
        setContent(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await postReview(content, movieId, title);
            await updateReviews();
            setTitle('');
            setContent('');
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <div>
            <h2 className="text-title">Leave a Review</h2>
            <div className="review-form-container">
                <form onSubmit={handleSubmit}>
                    <input
                        className="title-input"
                        type="text"
                        value={title}
                        onChange={handleTitleChange}
                        placeholder="Review Title"
                        required
                    />
                    <textarea
                        className="content-input"
                        value={content}
                        onChange={handleContentChange}
                        placeholder="Write your review here"
                        required
                    ></textarea>
                    <button className="button-submit" type="submit">Submit Review</button>
                </form>
            </div>
        </div>
    );
};

CreateReviewForm.propTypes = {
    movieId: PropTypes.number.isRequired,
    updateReviews: PropTypes.func.isRequired,
};

export default CreateReviewForm;
