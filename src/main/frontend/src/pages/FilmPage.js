import {useParams} from 'react-router-dom';
import {useEffect, useState} from 'react';
import axios from 'axios';
import ReactPlayer from 'react-player';
import './FilmPage.css';

const FilmPage = () => {
    const {id} = useParams();

    const [movie, setMovie] = useState([]);
    const [back, setBack] = useState([]);
    const [trailerKey, setTrailerKey] = useState('');

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization:
                'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0',
        },
    };

    const getMovie = async () => {
        try {
            const responseMovieDetails = await axios.get(
                `https://api.themoviedb.org/3/movie/${id}`,
                options
            );
            const responseMovieBack = await axios.get(
                `https://api.themoviedb.org/3/movie/${id}/images`,
                options
            );

            setMovie(responseMovieDetails.data);
            setBack(responseMovieBack.data.backdrops[0].file_path);
        } catch (err) {
            console.log(err);
        }
    };

    const getTrailer = async () => {
        try {
            const responseVideos = await axios.get(
                `https://api.themoviedb.org/3/movie/${id}/videos`,
                options
            );

            if (responseVideos.data.results.length > 0) {
                const trailer = responseVideos.data.results.find(
                    (video) => video.type === 'Trailer'
                );

                if (trailer) {
                    setTrailerKey(trailer.key);
                }
            }
        } catch (err) {
            console.log(err);
        }
    };

    useEffect(() => {
        getMovie();
        getTrailer();
    }, []);

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';
    const trailerUrl = `https://www.youtube.com/watch?v=${trailerKey}`;

    return (
        <div className="film-info-container">
            <div>
                <div
                    className="film-header"
                    style={{backgroundImage: `url(${path + back})`}}
                ></div>
                <div className="film-media-container">
                    <div className="film-poster">
                        <img src={path + movie.poster_path} alt="Movie poster"/>
                    </div>
                    <div className="film-details">
                        <h1>{movie.title}</h1>
                        <p>Release Date: {movie.release_date}</p>
                        <p>Runtime: {movie.runtime} minutes</p>
                        {movie.genres && movie.genres.length > 0 && (
                            <p>Genres: {movie.genres.map((genre) => genre.name).join(", ")}</p>
                        )}
                        <p>Revenue: {movie.revenue} $</p>
                        <p>Overview: {movie.overview}</p>
                    </div>
                </div>
                <div className="film-media-container">
                    <div className="player-wrapper">
                        {trailerKey && (
                            <ReactPlayer
                                className="react-player"
                                url={trailerUrl}
                                width="100%"
                                height="100%"
                                controls={true}
                            />
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export {FilmPage};
