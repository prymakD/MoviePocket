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
            const response = await axios.post('http://localhost:8080/login', {
                username: email,
                password: password,
            },{withCredentials: true});

            console.log('Login successful!', response.data);

            const authToken = response.headers['set-cookie'];

            // Сохранение куки на клиенте
            document.cookie = `authToken=${authToken.join(';')}`;
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
                <button onClick={handleLogout}>Logout</button>
            </form>
        </div>
    );
};

export default LoginPage;