import axios from "axios";


// getUserByUsername
export const getUser = async (username) => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/user/${username}`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
        return false
    }
}

// getUsernameByAuth
export const getUsernameByAuth = async () => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/user/getUsernameAut`,
            options
        );
        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// checkAuthentication
export const checkAuth = async () => {

    const options = {
        withCredentials: true
    }

    try {
        const response = await axios.get(
            `http://localhost:8080/user/getAut`,
            options
        );
        return true;
    } catch (err) {
        return false;
    }
}