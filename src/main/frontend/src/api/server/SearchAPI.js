import axios from "axios";


// getAllSearchedMovies
export const getMovieInfoSearch = async (query) => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/search/${query}`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}