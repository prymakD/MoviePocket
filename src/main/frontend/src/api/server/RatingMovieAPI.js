import axios from "axios";
import queryString from "query-string";


// Get all ratings for a user
export const getAllRatingByUser = async () => {

    const options = {
        withCredentials: true
    }

    try {
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

    const options = {
        withCredentials: true
    }

    try {
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

    const params = {
        MovieId: idMovie
    };

    try {
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

    const options = {
        withCredentials: true
    }

    try {
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

    const params = {
        MovieId: idMovie,
        rating: rating
    };

    try {
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