import axios from "axios";


// getAllSearchedMovies
export const getMovieInfoSearch = async (query) => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080http://localhost:8080/movies/search/${query}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}