import React from 'react';
import PropTypes from 'prop-types';
import './Pagination.css'

const Pagination = ({ currentPage, totalPages, onPreviousPage, onNextPage }) => {
    const handlePreviousPage = () => {
        if (currentPage > 1) {
            onPreviousPage();
        }
    };

    const handleNextPage = () => {
        if (currentPage < totalPages) {
            onNextPage();
        }
    };

    return (
        <div className="pagination">
            <button
                className="pagination-button"
                onClick={handlePreviousPage}
                disabled={currentPage === 1}
            >
                &lt;
            </button>
            <span className="pagination-info">
        Page {currentPage} of {totalPages}
      </span>
            <button
                className="pagination-button"
                onClick={handleNextPage}
                disabled={currentPage === totalPages}
            >
                &gt;
            </button>
        </div>
    );
};

Pagination.propTypes = {
    currentPage: PropTypes.number.isRequired,
    totalPages: PropTypes.number.isRequired,
    onPreviousPage: PropTypes.func.isRequired,
    onNextPage: PropTypes.func.isRequired,
};

export default Pagination;
