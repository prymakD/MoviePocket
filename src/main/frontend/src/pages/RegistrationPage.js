import React, {useEffect, useState} from 'react';
import './RegistrationPage.css';
import {postRegistration} from "../api/server/AuthenticationAPI";
import {getRandomMovie} from "../api/tmdb/MovieAPI";

const RegistrationPage = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [backgroundImage, setBackgroundImage] = useState('');


    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await postRegistration(username, email, password);
        } catch (error) {
            console.log(error);
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
        getRandomMovieImage();
    }, []);

    return (
        <div
            className="image-container"
            style={{backgroundImage: `url(${backgroundImage})`}}
        >
            <div className="registration-container">
                <h1 className="head">CREATE ACCOUNT</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <br/>
                        <input
                            className="registration-input"
                            type="text"
                            id="username"
                            value={username}
                            onChange={handleUsernameChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <br/>
                        <input
                            className="registration-input"
                            type="email"
                            id="email"
                            value={email}
                            onChange={handleEmailChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <br/>
                        <input
                            className="registration-input"
                            type="password"
                            id="password"
                            value={password}
                            onChange={handlePasswordChange}
                        />
                    </div>
                    <button className="button_sign_in" type="submit">Join Us</button>
                </form>
            </div>
        </div>
    );
};

export default RegistrationPage;