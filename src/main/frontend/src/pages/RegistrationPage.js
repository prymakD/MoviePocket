import React, {useEffect, useState} from 'react';
import axios from 'axios';
import './RegistrationPage.css';
import queryString from "query-string";

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
            const params = {
                username: username,
                email: email,
                password: password,
            }
            const response = await axios.post('http://localhost:8080/registration', queryString.stringify(params));

            console.log('Registration successful!', response.data);
        } catch (error) {
            console.error('Error occurred during registration:', error);
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