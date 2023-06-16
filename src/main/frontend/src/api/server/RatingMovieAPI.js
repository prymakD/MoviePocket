import axios from "axios";
import queryString from "query-string";


// Get all ratings for a user
export const getAllRatingByUser = async () => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/movies/rating/allByUser`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// getCountMovieRating
export const getCountMovieRating = async (idMovie) => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/movies/rating/count/rating?id=${idMovie}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// Remove the rating for a movie
export const removeRating = async (idMovie) => {
    try {
        const params = {
            MovieId: idMovie
        };

        const response = await axios.post(
            `http://localhost:8080/movies/rating/del`,
            queryString.stringify(params),
            { withCredentials: true },
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Get the rating for a movie
export const getMovieRating = async (idMovie) => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/movies/rating/count/rating?MovieId=${idMovie}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// Set the rating for a movie
export const postMovieRating = async (idMovie, rating) => {
    try {
        const params = {
            MovieId: idMovie,
            rating: rating
        };

        const response = await axios.post(
            `http://localhost:8080/movies/rating/set`,
            queryString.stringify(params),
            { withCredentials: true },
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
};