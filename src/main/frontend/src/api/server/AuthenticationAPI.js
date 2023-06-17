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
        // Save cookie on the client
        document.cookie = `authToken=${authToken.join(';')}`;
        return response
    } catch (err) {
        console.log(err);
    }
};

// registration
export const postRegistration = async (username, email, password) => {

    const params = {
        username: username,
        email: email,
        password: password,
    }

    try {
        const response = await axios.post(
            'http://localhost:8080/registration',
            queryString.stringify(params));
        return response.data;
    } catch (error) {
        console.error('Error occurred during registration:', error);
    }
};

// not ready function
export const postLogout = async () => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            'http://localhost:8080/user',
            options);
        return response.data;
    } catch (error) {
        console.error('Error occurred during logout:', error);
    }
};