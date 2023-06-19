import axios from "axios";
import queryString from "query-string";

// login
export const postLogin = async (email, password) => {

    const params = {
        username: email,
        password: password,
    };

    try {
        const response = await axios.post(
            'http://localhost:8080/login',
            queryString.stringify(params),
            {withCredentials: true}
        );
        const authToken = response.headers['set-cookie'];

        if (Array.isArray(authToken)) {
            const cookieValue = authToken.join(';');
            document.cookie = `authToken=${cookieValue}`;
        } else if (typeof authToken === 'string') {
            document.cookie = `authToken=${authToken}`;
        }
        return true
    } catch (err) {
        console.log(err);
        return false
    }
};

// registration
export const postRegistration = async (username, email, password) => {

    const params = {
        username: username,
        email: email,
        password: password,
    }

    const response = await axios.post(
        'http://localhost:8080/registration',
        queryString.stringify(params));
    return response;

};

// post activate
export const postActivateUser = async (token) => {

    const params = {
        token: token
    }
    try {
        const response = await axios.post(
            'http://localhost:8080/activate',
            queryString.stringify(params));
        return response.data;
    } catch (error) {
        console.log(error);
    }

};

// log out
export const postLogout = async () => {

    const params = {
        withCredentials: true
    }

    try {
        const response = await axios.post(
            'http://localhost:8080/logout',
            queryString.stringify(params),
            {withCredentials: true}
        );
        return response.data;
    } catch (error) {
        console.error('Error occurred during logout:', error);
    }
};