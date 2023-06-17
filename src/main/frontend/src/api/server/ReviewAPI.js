import axios from "axios";
import queryString from "query-string";


// delMovieReview
export const delReview = async(idReview) => {

    const params = {
        idReview: idReview
    }

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/review/del`,
            queryString.stringify(params),
            {withCredentials: true},
        )
        return response.data
    } catch (err) {
        console.log(err)
    }
};

// setMovieReview
export const postReview = async (content, idMovie, title) => {

    const params = {
        content: content,
        idMovie: idMovie,
        title: title
    }

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/review/set`,
            queryString.stringify(params),
            {withCredentials: true},
        )
        return response.data
    } catch (err) {
        console.log(err)
    }
};

// setLike
export const postReviewLike = async (idReview, like) => {

    const params = {
        id: idReview,
        like: like
    }

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/review/setlike`,
            queryString.stringify(params),
            {withCredentials: true},
        )
        return response.data
    } catch (err) {
        console.log(err)
    }
};

// setUpdateMovieReview
export const postReviewUpdate = async (content, idReview, title) => {

    const params = {
        content: content,
        idReview: idReview,
        title: title
    }

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/review/up`,
            queryString.stringify(params),
            {withCredentials: true},
        )
        return response.data
    } catch (err) {
        console.log(err)
    }
};

// getByIdReview
export const getReviewById = async (idReview) => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/review/get?idReview=${idReview}`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }   
}

// getAllReviewByIdMovie
export const getAllReview = async (idMovie) => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/review/getAllByMovie?idMovie=${idMovie}`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// getAllReviewByUser
export const getAllReviewByUser = async () => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/review/getAllMy`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}