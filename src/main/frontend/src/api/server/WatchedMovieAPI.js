import axios from 'axios';
import queryString from "query-string";

export const postWatchedMovie = async (idMovie) => {
    try {
        const params = {
            idMovie: idMovie
        };

        const response = await axios.post(
            'http://localhost:8080/movies/watched/set',
            queryString.stringify(params),
            { withCredentials: true }
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
};

export const getWatchedMovie = async (idMovie) => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/movies/watched/get?idMovie=${idMovie}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}