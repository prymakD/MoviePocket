import {Link, useParams} from 'react-router-dom'
import {useEffect, useState} from "react";
import axios from "axios";
import './FilmsBrowsingPage.css'

const FilmsBrowsingPage = () => {
    const [movies, setMovies] = useState([]);

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };

    const getMovies = async () => {
        try {
            const response = await axios.get(`https://api.themoviedb.org/3/movie/popular`, options);
            console.log(response.data.results)
            setMovies(response.data.results)
        } catch (err) {
            console.log(err);
        }
    }

    useEffect(() => {
        getMovies();
    }, [])

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';

    return (
        <div className='films-browser-container'>
            <h1>Movie Browser</h1>
            <div className="film-list">
                {movies.map(movie => (
                    <div className="film-card">
                        <Link to={`/films/${movie.id}`}>
                            <img src={path+movie.poster_path}  alt="movie-poster"/>
                        </Link>
                        <Link to={`/films/${movie.id}`}>
                            <h2>{movie.title}</h2>
                        </Link>
                        <div>{movie.overview}</div>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default FilmsBrowsingPage;