import React, {useEffect, useState} from 'react';
import './ActivateUserPage.css';
import {useLocation} from 'react-router-dom';
import {getRandomMovie} from "../api/tmdb/MovieAPI";
import {toast, ToastContainer} from "react-toastify";
import {postActivateUser} from "../api/server/AuthenticationAPI";
import queryString from "query-string";

const LostPasswordPage = () => {
    const location = useLocation();
    const [backgroundImage, setBackgroundImage] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            //activation of user
            const responseActivate = await postActivateUser(queryString.parse(location.search).token);
            toast.success('You were successfully activated', {
                position: toast.POSITION.TOP_CENTER,
            });
            window.location.href = '/login'
            console.log(responseActivate);
        } catch (error) {
            toast.error('Ups... Something went wrong \n Please try again:)', {
                position: toast.POSITION.TOP_CENTER,
            });
            console.error('Error occurred while activating user:', error);
        }
    };

    const getRandomMovieImage = async () => {
        try {
            const response = await getRandomMovie();
            setBackgroundImage(`https://image.tmdb.org/t/p/original${response.backdrop_path}`);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getRandomMovieImage().then();
    }, []);

    return (
        <div
            className="image-activate-container"
            style={{backgroundImage: `url(${backgroundImage})`}}
        >
            <div className="activate-container">
                <h1 className="head-activate">User Activation</h1>
                <form onSubmit={handleSubmit}>
                    <button className="activate-button" type="submit">
                        Activate
                    </button>
                </form>
            </div>
            <ToastContainer/>
        </div>
    );
};

export default LostPasswordPage;
