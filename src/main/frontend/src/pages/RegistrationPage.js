import React, { useState } from 'react';
import axios from 'axios';
import './RegistrationPage.css';
import queryString from "query-string";

const RegistrationPage = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

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
            const response = await axios.post('http://localhost:8080/registration', queryString.stringify(params),{withCredentials: true});

            console.log('Registration successful!', response.data);
        } catch (error) {
            console.error('Error occurred during registration:', error);
        }
    };

    return (
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
                <button type="submit">Join Us</button>
            </form>
        </div>
    );
};

export default RegistrationPage;