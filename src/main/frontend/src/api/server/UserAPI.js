import axios from "axios";


// getUserByUsername
export const getUser = async (username) => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/user/${username}`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}

// checkAuthentication
export const checkAuth = async () => {
    try {
        const options = {
            withCredentials: true
        }
        const response = await axios.get(
            `http://localhost:8080/user/getAut`,
            options
        );

        return response.data;
    } catch (err) {
        console.log(err);
    }
}