import axios from "axios";

export const getMovieSearch = async (query) => {

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };

    try {
        const response = await axios.get(
            `https://api.themoviedb.org/3/search/movie?query=${query}`,
            options
        );
        return response.data
    } catch (err) {
        console.log(err);
    }
};

export const getMovieDetails = async (id) => {

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };

    try {
        const response = await axios.get(
            `https://api.themoviedb.org/3/movie/${id}`,
            options
        );
        console.log(response)
        return response.data
    } catch (err) {
        console.log(err);
    }
};

export const getMovieBackDropImage = async (id) => {

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };

    try {
        const response = await axios.get(
            `https://api.themoviedb.org/3/movie/${id}/images`,
            options
        );
        return response.data.backdrops[0].file_path
    } catch (err) {
        console.log(err);
    }
};

export const getRandomMovie = async () => {

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };

    try {
        const response = await axios.get(
            'https://api.themoviedb.org/3/movie/popular',
            options
        );
        const randomIndex = Math.floor(
            Math.random() * response.data.results.length
        );
        return response.data.results[randomIndex];
    } catch (err) {
        console.log(err);
    }
};

export const getMovieTrailer = async (id) => {

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization:
                'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0',
        },
    };

    try {
        const response = await axios.get(
            `https://api.themoviedb.org/3/movie/${id}/videos`,
            options
        );

        const trailerUrl = `https://www.youtube.com/watch?v=`;

        if (response.data.results.length > 0) {
            const trailer = response.data.results.find(
                (video) => video.type === 'Trailer'
            );
            return trailerUrl + trailer.key
        }
        else return false
    } catch (err) {
        console.log(err);
    }
};

export const getUpComingMovies = async () => {

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization:
                'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0',
        },
    };

    try {
        const response = await axios.get(
            'https://api.themoviedb.org/3/movie/upcoming?language=en-US&page=1',
            options
        );
        return response.data.results
    } catch (err) {
        console.log(err);
    }
}