import React, {useEffect, useState} from 'react';
import './LoginPage.css';
import {postLogin} from "../api/server/AuthenticationAPI";
import {Link} from "react-router-dom";
import {getRandomMovie} from "../api/tmdb/MovieAPI";
import {toast, ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';


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
            if (response) {
                toast.success('You are successfully logged in)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                window.location.href = '/'
            } else {
                console.log(response)
                toast.error('Ups... Seems like you provided wrong password or email. \n Please try again:)', {
                    position: toast.POSITION.TOP_CENTER,
                });
            }
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
        getRandomMovieImage().then();
    }, []);


    return (
        <div
            className="image-login-container"
            style={{backgroundImage: `url(${backgroundImage})`}}
        >
            <div className="login-container"
            >
                <h1 className="head-login">SIGN IN</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group-login">
                        <label htmlFor="email">Email</label>
                        <br/>
                        <input
                            className="registration-input-login"
                            type="email"
                            id="email"
                            value={email}
                            onChange={handleEmailChange}
                        />
                    </div>
                    <div className="form-group-login">
                        <label htmlFor="password">Password</label>
                        <br/>
                        <input
                            className="registration-input-login"
                            type="password"
                            id="password"
                            value={password}
                            onChange={handlePasswordChange}
                        />
                    </div>
                    <button className="button-login_sign_in" type="submit">Sign in</button>
                    <Link to={`/forgotPassword`}>
                        <button className="link-primary">Forgot password</button>
                    </Link>
                </form>
            </div>
            <ToastContainer/>
        </div>
    );
};

export default LoginPage;
