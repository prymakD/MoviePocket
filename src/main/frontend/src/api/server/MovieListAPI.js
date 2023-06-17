import axios from "axios";
import queryString from "query-string";


// Delete movie list and all that it had(movies in it and likes from other 2 tables)
export const deleteMovieList = async (idMovieList) => {

    const params = {
        idMovieList : idMovieList
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/list/del`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Create a new movie list
export const createMovieList = async (content, title) => {

    const params = {
        content: content,
        title: title
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/list/set`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Like or dislike movie list
export const likeMovieList = async (idList, like) => {

    const params = {
        idList: idList,
        like: like
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/list/setLike`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Add or delete movie from list
export const addToMovieList = async (idList, idMovie) => {

    const params = {
        idList: idList,
        idMovie: idMovie
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/list/setMovie`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Set or delete category(tag)
export const addTagToList = async (idCategory, idList) => {

    const params = {
        idCategory: idCategory,
        idList: idList
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/list/setOrDeleteCategory`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Update movie list content
export const updateListContent = async (content, idMovieList) => {

    const params = {
        content: content,
        idMovieList: idMovieList
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/list/updateContent`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Update movie list title
export const updateListTitle = async (idMovieList, title) => {

    const params = {
        idMovieList: idMovieList,
        title : title
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/movies/list/updateTitle`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Get movie list
export const getMovieList = async (idMovieList ) => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/list/get?idMovieList=${idMovieList}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// Get all movie lists in the system
export const getAllMovieLists = async () => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/list/getAllList`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// Get all my movie lists
export const getAllMyLists = async () => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/list/getAllMyLists`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// Get all movie lists of user
export const getAllUserLists = async (username) => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/list/getAllUserLists?username=${username}`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// Get movie list by title
export const getMovieListByTitle = async (idMovieList) => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/movies/list/getByTitle?idMovieList=${idMovieList}`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}