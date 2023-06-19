import React, {useEffect, useState} from 'react';
import './NewPasswordPage.css';
import {useLocation} from 'react-router-dom';
import {postResetPassword} from "../api/server/LostPassAPI";
import {getRandomMovie} from "../api/tmdb/MovieAPI";
import queryString from "query-string";
import {toast, ToastContainer} from "react-toastify";

const LostPasswordPage = () => {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const location = useLocation();
    const [backgroundImage, setBackgroundImage] = useState('');


    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleConfirmPasswordChange = (event) => {
        setConfirmPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            if (password !== confirmPassword) {
                toast.error('Ups... Seems like your passwords don\'t match. \n Please try again:)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                console.error('Passwords doesnt match')
            } else {
                const response = await postResetPassword(queryString.parse(location.search).token, password, confirmPassword);
                console.log('Password reset successful!', response.data);
                window.location.href = '/login'
            }
        } catch (error) {
            toast.error('Ups... Something went wrong \n Please try again:)', {
                position: toast.POSITION.TOP_CENTER,
            });
            console.error('Error occurred while resetting password:', error);
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
            className="image-newpass-container"
            style={{backgroundImage: `url(${backgroundImage})`}}
        >
            <div className="newpass-container">
                <h1 className="head-newpass">Reset Password</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group-newpass">
                        <label htmlFor="password">New Password</label>
                        <br/>
                        <input
                            className="newpass-password-input"
                            type="password"
                            id="password"
                            value={password}
                            onChange={handlePasswordChange}
                        />
                    </div>
                    <div className="form-group-newpass">
                        <label htmlFor="confirmPassword">Confirm Password</label>
                        <br/>
                        <input
                            className="newpass-password-input"
                            type="password"
                            id="confirmPassword"
                            value={confirmPassword}
                            onChange={handleConfirmPasswordChange}
                        />
                    </div>
                    <button className="newpass-password-button" type="submit">
                        Reset Password
                    </button>
                </form>
            </div>
            <ToastContainer/>
        </div>
    );
};

export default LostPasswordPage;
