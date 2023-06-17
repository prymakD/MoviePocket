import axios from "axios";
import queryString from "query-string";


// setMail
export const postLostPasswordSetMail = async (username) => {

    const params = {
        username: username
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/lostpassword/`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};

// resetPassword
export const postResetPassword = async (password0, password1) => {

    const params = {
        password0: password0,
        password1: password1
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/lostpassword/reset`,
            queryString.stringify(params),
            { withCredentials: true },
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
};