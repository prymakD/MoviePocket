import { useParams } from 'react-router-dom'
import {useEffect, useState} from "react";
import axios from "axios";
import ReactPlayer from 'react-player';
import './FilmPage.css';
const FilmPage = () => {
    const {id} = useParams()

    const [movie, setMovie] = useState([]);
    const [back, setBack] = useState([]);
    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };
    const getMovie = async () => {
        try {
            const responseMovieDetails = await axios.get(`https://api.themoviedb.org/3/movie/${id}`, options);
            const responseMovieBack = await axios.get(`https://api.themoviedb.org/3/movie/${id}/images`, options);
            console.log(responseMovieDetails.data)
            console.log(responseMovieBack.data.backdrops[0])
            setMovie(responseMovieDetails.data)
            setBack(responseMovieBack.data.backdrops[0].file_path)
        } catch (err) {
            console.log(err);
        }
    }

    useEffect(() => {
        getMovie();
    }, [])

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';
    return (
        <div className='film-info-container'>
            <div className='film-header' style={{backgroundImage: `url(${path + back})`}}></div>
            <h1>{movie.title}</h1>
            <div className="film-media-container">
                <div className='film-poster'>
                    <img src={path+movie.poster_path} alt="Movie poster" />
                </div>
                <div className="player-wrapper">
                    <ReactPlayer
                        url="https://www.youtube.com/watch?v=dQw4w9WgXcQ"
                        width="100%"
                        height="100%"
                        controls={true}
                    />
                </div>
            </div>
        </div>
    )
}

export {FilmPage};