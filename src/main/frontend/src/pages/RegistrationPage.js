import React, {useEffect, useState} from 'react';
import './RegistrationPage.css';
import {postRegistration} from '../api/server/AuthenticationAPI';
import {getRandomMovie} from '../api/tmdb/MovieAPI';
import {toast, ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

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
            if (response.status === 201) {
                toast.success('Registration successful! \n Please verify your email to activate your account. To do so go by the link that was sent to your email.', {
                    position: toast.POSITION.TOP_CENTER,
                });
                setUsername('');
                setEmail('');
                setPassword('');
            }
        } catch (error) {
            if (error.response && error.response.status === 403) {
                toast.error('User is already registered. Please try another email', {
                    position: toast.POSITION.TOP_CENTER,
                });

            } else if (error.response && error.response.status === 401) {
                toast.error('Sorry but this username is already occupied. Please try another one', {
                    position: toast.POSITION.TOP_CENTER,
                });

            } else if (error.response && error.response.status === 400) {
                toast.error(error.response.data, {
                    position: toast.POSITION.TOP_CENTER,
                });
            } else {
                toast.error('Ups. Something went wrong. Please try again.', {
                    position: toast.POSITION.TOP_CENTER,
                });
            }
            console.log(error);
        }
    };

    const getRandomMovieImage = async () => {
        try {
            const response = await getRandomMovie();
            setBackgroundImage(`https://image.tmdb.org/t/p/original${response.backdrop_path}`);
        } catch (error) {
            toast.error(error, {position: toast.POSITION.TOP_CENTER});
            console.log(error);
        }
    };

    useEffect(() => {
        getRandomMovieImage().then();
    }, []);

    return (
        <div className="image-container" style={{backgroundImage: `url(${backgroundImage})`}}>
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
                    <button className="button_sign_in" type="submit">
                        Join Us
                    </button>
                </form>
            </div>
            <ToastContainer/>
        </div>
    );
};

export default RegistrationPage;
