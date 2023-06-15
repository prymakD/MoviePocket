import React, {useEffect, useState} from 'react';
import axios from 'axios';
import './LoginPage.css';
import queryString from 'query-string';
import {Link} from "react-router-dom";

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

    const handleLogout = async () => {
        try {
            const response = await axios.get('http://localhost:8080/user/bob');
            console.log('Log out successful!', response.data);
        } catch (error) {
            console.error('Error occurred during logout:', error);
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

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

            console.log('Login successful!', response.data);

            const authToken = response.headers['set-cookie'];

            // Save cookie on the client
            document.cookie = `authToken=${authToken.join(';')}`;
        } catch (error) {
            console.error('Error occurred during login:', error);
        }
    };

    const options = {
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };

    useEffect(() => {
        const fetchRandomMovieImage = async () => {
            try {
                const response = await axios.get(
                    'https://api.themoviedb.org/3/movie/popular',
                    options
                );

                const randomIndex = Math.floor(
                    Math.random() * response.data.results.length
                );
                const movie = response.data.results[randomIndex];

                setBackgroundImage(`https://image.tmdb.org/t/p/original${movie.backdrop_path}`);
            } catch (error) {
                console.error('Error occurred while fetching movie image:', error);
            }
        };

        fetchRandomMovieImage();
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
