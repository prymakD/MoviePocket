import axios from "axios";
import queryString from "query-string";

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