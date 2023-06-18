import React, { useState } from 'react';
import {postReview} from "../../api/server/ReviewAPI";
import "./CreateReviewForm.css"


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
        <div className="review-form-container">
            <h2>Leave a Review</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={title}
                    onChange={handleTitleChange}
                    placeholder="Review Title"
                    required
                />
                <textarea
                    value={content}
                    onChange={handleContentChange}
                    placeholder="Write your review here"
                    required
                ></textarea>
                <button type="submit">Submit Review</button>
            </form>
        </div>
    );
};

export default CreateReviewForm;
