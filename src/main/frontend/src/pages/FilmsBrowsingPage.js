import {Link} from 'react-router-dom'
import {useEffect, useState} from "react";
import axios from "axios";
import './FilmsBrowsingPage.css'
import queryString from "query-string";

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
    const postWatched = async(idMovie) => {
        try{
            console.log(idMovie)
            const params = {
                idMovie: parseInt(idMovie)
            }
            const response = await axios.post(`http://localhost:8080/movies/watched/set`,
                                                queryString.stringify(params),
                                              {withCredentials: true})
            console.log(response.data)
        } catch (err) {
            console.log(err)
        }
    }

    useEffect(() => {
        getMovies();
    }, [])

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';

    return (
        <div className='films-browser-container'>
            <h1>Movie Browser</h1>
            <div className="films-browser-list">
                {movies.map(movie => (
                    <div className="film-browser-card">
                        <Link to={`/films/${movie.id}`}>
                            <div className='film-browser-poster'>
                                <img src={path + movie.poster_path} alt="movie-poster"/>
                            </div>
                        </Link>
                        <img src="https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/eye.png"
                             className="watched"
                             alt="watched"
                             onClick={() => postWatched(movie.id)}/>
                        <div className='film-browser-info'>
                            <div className='film-browser-title'>
                                <Link to={`/films/${movie.id}`}>
                                    <h2>{movie.title}</h2>
                                </Link>
                            </div>
                            <div>{movie.overview}</div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    )
}

export default FilmsBrowsingPage;