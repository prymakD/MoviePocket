import React, {useEffect, useState} from 'react';
import './LoginPage.css';
import {postLogin} from "../api/server/AuthenticationAPI";
import {Link} from "react-router-dom";
import {getRandomMovie} from "../api/tmdb/MovieAPI";

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [backgroundImage, setBackgroundImage] = useState('');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await postLogin(email, password);
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
            <div className="login-container"
            >
                <h1 className="head">SIGN IN</h1>
                <form onSubmit={handleSubmit}>
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
                    <button className="button_sign_in" type="submit">Sign in</button>
                    <Link to={`/forgotPassword`}>
                        <button className="link-primary">Forgot password</button>
                    </Link>
                </form>
            </div>
        </div>
    );
};

export default LoginPage;
