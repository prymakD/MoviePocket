import React, {useEffect, useState} from 'react';
import './LostPasswordPage.css';
import {getRandomMovie} from "../api/tmdb/MovieAPI";
import {postLostPasswordSetMail} from "../api/server/LostPassAPI";
import {toast, ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';

const LostPasswordPage = () => {
    const [email, setEmail] = useState('');
    const [backgroundImage, setBackgroundImage] = useState('');


    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const params = {
            email: email
        }

        try {
            const response = await postLostPasswordSetMail(email);

            if (response === false) {
                toast.error('Ups... Seems like you provided wrong email. \n Please try again:)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                console.error('Error occurred while sending password reset email:', response);

            } else {
                toast.success('The confirmation link was successfully sent \n Please check your email', {
                    position: toast.POSITION.TOP_CENTER,
                });
                console.log('Password reset email sent!', response);

            }
        } catch (error) {
            console.error('Error occurred while sending password reset email:', error);
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
            className="image-lost-container"
            style={{backgroundImage: `url(${backgroundImage})`}}
        >
            <div className="lost-password-container">
                <h1 className="head-lost">LOST PASSWORD</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group-lost">
                        <label htmlFor="email">Provide your email</label>
                        <br/>
                        <input
                            className="lost-password-input"
                            type="email"
                            id="email"
                            value={email}
                            onChange={handleEmailChange}
                        />
                    </div>
                    <button className="lost-password-button" type="submit">
                        Reset Password
                    </button>
                </form>
            </div>
            <ToastContainer/>
        </div>
    );
};

export default LostPasswordPage;
