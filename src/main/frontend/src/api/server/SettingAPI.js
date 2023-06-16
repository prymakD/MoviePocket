import axios from "axios";
import queryString from "query-string";


// Delete a user
export const deleteUser = async (password) => {
    try {
        const params = {
            password: password
        };

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
    try {
        const params = {
            bio: bio
        };

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
    try {
        const params = {
            email: email
        };

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
    try {
        const params = {
            password0: password0,
            password1: password1,
            passwordold: passwordold
        };

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
    try {
        const params = {
            username: username
        };

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