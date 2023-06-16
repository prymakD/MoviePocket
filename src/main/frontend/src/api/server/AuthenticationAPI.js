import axios from "axios";
import queryString from "query-string";

// login
export const postLogin = async (email, password) => {
    try {
        const params = {
            username: email,
            password: password,
        };

        const response = await axios.post(
            'http://localhost:8080/login',
            queryString.stringify(params),
            {withCredentials: true}
        );

        const authToken = response.headers['set-cookie'];

        // Save cookie on the client
        document.cookie = `authToken=${authToken.join(';')}`;

        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// registration
export const postRegistration = async (username, email, password) => {
    try {
        const params = {
            username: username,
            email: email,
            password: password,
        }
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
    try {
        const response = await axios.get('http://localhost:8080/user');
        console.log('Log out successful!', response.data);
    } catch (error) {
        console.error('Error occurred during logout:', error);
    }
};