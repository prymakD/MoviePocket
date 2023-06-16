import axios from "axios";
import queryString from "query-string";


// allUserToWatchMovies
export const getAllToWatch = async () => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/movies/towatch/all`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// getAllCountToWatchByIdMovie
export const getAllCountToWatch = async (idMovie) => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/movies/towatch/all?id=${idMovie}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// Check if a user have in to watch
export const getToWatch = async (idMovie) => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/movies/towatch/get?id=${idMovie}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// setOrDeleteMovieToWatch
export const postToWatchMovie = async (idMovie) => {
    try {
        const params = {
            id: idMovie
        };

        const response = await axios.post(
            `http://localhost:8080/movies/towatch/set`,
            queryString.stringify(params),
            { withCredentials: true },
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
};