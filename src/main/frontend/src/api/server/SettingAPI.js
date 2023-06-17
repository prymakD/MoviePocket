import axios from "axios";
import queryString from "query-string";


// Delete a user
export const deleteUser = async (password) => {

    const params = {
        password: password
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/user/edit/delete`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Set a new bio
export const postNewBio = async (bio) => {

    const params = {
        bio: bio
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/user/edit/newbio`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Set a new email
export const postNewEmail = async (email) => {

    const params = {
        email: email
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/user/edit/newemail`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Set a new password(password is validated)
export const postNewPassword = async (password0, password1, passwordold) => {

    const params = {
        password0: password0,
        password1: password1,
        passwordold: passwordold
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/user/edit/newpas`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// Set a new username
export const postNewUsername = async (username) => {

    const params = {
        username: username
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/user/edit/newusername`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};