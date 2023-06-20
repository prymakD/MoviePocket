import React, {useState} from 'react';

const RatingComponent = () => {
    const [rating, setRating] = useState(null);

    const handleRatingChange = (value) => {
        setRating(value);
    };

    return (
        <div>
            <div>
                {[...Array(10)].map((_, index) => (
                    <span
                        key={index}
                        onClick={() => handleRatingChange(index + 1)}
                        style={{
                            cursor: 'pointer',
                            color: index < rating ? 'gold' : 'gray',
                        }}
                    >
            ✩
          </span>
                ))}
            </div>
            <div>
                {rating && (
                    <div>
                        <h3>Selected rating: {rating}</h3>
                    </div>
                )}
            </div>
        </div>
    );
};

export default RatingComponent;


