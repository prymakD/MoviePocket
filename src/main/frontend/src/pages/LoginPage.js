import React, { useState } from 'react';
import axios from 'axios';
import './LoginPage.css';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://localhost:8080/login', {
                username: email,
                password: password,
            });

            console.log('Login successful!', response.data);
            const authToken = response.headers['set-cookie'];

            console.log(authToken);
            document.cookie = authToken;
        } catch (error) {
            console.error('Error occurred during login:', error);
        }
    };

    return (
        <div className="registration-container">
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
                <button type="submit">Sign in</button>
            </form>
        </div>
    );
};

export default LoginPage;