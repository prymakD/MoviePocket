import {Link} from 'react-router-dom'
import {useEffect, useState} from "react";
import axios from "axios";
import './FilmsBrowsingPage.css'
import queryString from "query-string";

const FilmsBrowsingPage = () => {
    const [movies, setMovies] = useState([]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const [watchedCounts, setWatchedCounts] = useState({});

    const options = {
        method: 'GET',
        headers: {
            accept: 'application/json',
            Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
        }
    };

    const getMovies = async (page) => {
        try {
            if (page > 6) {
                setMovies([]);
                setTotalPages(6);
                return;
            }

            const response = await axios.get(
                `https://api.themoviedb.org/3/movie/popular?page=${page}`,
                options
            );

            setMovies(response.data.results);
            setTotalPages(response.data.total_pages > 6 ? 6 : response.data.total_pages);
        } catch (err) {
            console.log(err);
        }
    }

    const getCountWatched = async(idMovie) => {
        try{
            const params = {
                id: idMovie
            }
            const config = {
                params: params,
                withCredentials: true
            }
            const response = await axios.get(`http://localhost:8080/movies/watched/count/watched`, config)
            console.log(response.data)
            setWatchedCounts((prevCounts) => ({
                ...prevCounts,
                [idMovie]: response.data,
            }));
        } catch (err) {
            console.log(err)
        }
    }

    const postWatched = async(idMovie) => {
        try{
            const params = {
                idMovie: idMovie
            }

            const response = await axios.post(`http://localhost:8080/movies/watched/set`,
                                                queryString.stringify(params),
                                              {withCredentials: true},
            )
            console.log(response.data)
        } catch (err) {
            console.log(err)
        }
    }

    const getWatchedMovie = async (idMovie) => {
        try {
            const params = {
                idMovie: idMovie
            }
            const config = {
                params: params,
                withCredentials: true
            }
            const response = await axios.get(
                `http://localhost:8080/movies/watched/get`,
                config
            );
            console.log(response.data);
        } catch (err) {
            console.log(err);
        }
    }

    const postLiked = async(idMovie) => {
        try{
            const params = {
                idMovie: idMovie
            }
            const response = await axios.post(`http://localhost:8080/movies/favorite/set`,
                queryString.stringify(params),
                {withCredentials: true},
            )
            console.log(response.data)
        } catch (err) {
            console.log(err)
        }
    }

    useEffect(() => {
        getMovies(currentPage);
        getWatchedMovie(1);
    }, [currentPage]);

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';

    const handlePreviousPage = () => {
        if (currentPage > 1) {
            setCurrentPage(currentPage - 1);
        }
    };

    const handleNextPage = () => {
        if (currentPage < totalPages) {
            setCurrentPage(currentPage + 1);
        }
    };

    return (
        <div className="films-browser-container">
            <h1>Movie Browser</h1>
            <div className="films-browser-list">
                {movies.map(movie => (
                    <div className="film-browser-card" key={movie.id}>
                        <div className="film-browser-poster">
                            <Link to={`/films/${movie.id}`}>
                                <img src={path + movie.poster_path}
                                     className="poster-card"
                                     alt="movie-poster"/>
                            </Link>
                            <div className="film-poster-buttons">
                                <img src="https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/eye.png"
                                     className="watched"
                                     alt="watched"
                                     onClick={() => postWatched(movie.id)}/>
                                <img src="https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/likefalse.png"
                                     className="like"
                                     alt="like"
                                     onClick={() => postLiked(movie.id)}/>
                            </div>
                        </div>
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
            {totalPages > 1 && ( // Update the condition here
                <div className="pagination">
                    <button
                        className="pagination-button"
                        onClick={handlePreviousPage}
                        disabled={currentPage === 1}
                    >
                        &lt;
                    </button>
                    <span className="pagination-info">
                    Page {currentPage} of {totalPages}
                </span>
                    <button
                        className="pagination-button"
                        onClick={handleNextPage}
                        disabled={currentPage === totalPages}
                    >
                        &gt;
                    </button>
                </div>
            )}
        </div>
    )
}

export default FilmsBrowsingPage;
