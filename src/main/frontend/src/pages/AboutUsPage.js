import React, {useEffect, useState} from 'react';
import {getRandomMovie} from "../api/tmdb/MovieAPI";
import './SettingsPage.css';
import './AboutUsPage.css';


const AboutUsPage = () => {
    const [backgroundImage, setBackgroundImage] = useState('');


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
            <div className="main">
                <div className="settings-container">
                    <div className="about-content">
                        <h1>About Us</h1>
                        <p>
                            Our main idea is to create a place where people can express their love
                            for movies and share that love with other users. Our service will
                            allow people to keep track of their movie-watching history, create
                            their own lists of favorite movies according to their preferences, and
                            share them with others to discover people's different tastes in
                            movies.
                        </p>
                    <p>
                        It's important to say that users won't be able to watch movies on our
                        platform, which we think helps keep our platform as social as
                        possible, reducing distractions from other features that aren't
                        connected to it. We also plan to add a review module.
                    </p>
                        <p>
                            People will be able to rate a video and leave a short review about it.
                            The target audience for our website will be people who want to connect
                            with a community around their favorite movies from different genres
                            and have a tool that will help organize their own movie collection in
                            an easy way.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AboutUsPage;