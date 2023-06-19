import axios from "axios";
import queryString from "query-string";


// setMail
export const postLostPasswordSetMail = async (email) => {

    const params = {
        email: email
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/lostpassword/setEmail`,
            queryString.stringify(params),
            {withCredentials: true},
        );
        return response.data;
    } catch (err) {
        console.log(err);
        return false;
    }
};

// resetPassword
export const postResetPassword = async (token, password0, password1) => {

    const params = {
        token: token,
        password0: password0,
        password1: password1
    };

    try {
        const response = await axios.post(
            `http://localhost:8080/lostpassword/reset`,
            queryString.stringify(params),
            {withCredentials: true},
        );
        return response.data;
    } catch (error) {
        if (error.response) {
            console.error('Error occurred while resetting password:', error.response.data);
        } else if (error.message) {
            console.error('Error occurred while resetting password:', error.message);
        } else {
            console.error('Error occurred while resetting password:', error);
        }
    }
};