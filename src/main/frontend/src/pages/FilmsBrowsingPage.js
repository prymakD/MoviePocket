import {Link, useParams, useNavigate} from 'react-router-dom'
import {useEffect, useState} from "react";
import axios from "axios";
import './FilmsBrowsingPage.css'
import queryString from "query-string";

const FilmsBrowsingPage = () => {
    const {currentPage} = useParams();
    const navigate = useNavigate();

    const [movies, setMovies] = useState([]);
    const [totalPages, setTotalPages] = useState(6);

    const getMovies = async (page) => {
        try {

            const options = {
                method: 'GET',
                headers: {
                    accept: 'application/json',
                    Authorization: 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxZGEzNWQ1OGZkMTI0OTdiMTExZTRkZDFjNGE0YzAwNCIsInN1YiI6IjY0NDUyZGMwNjUxZmNmMDYxNzliZmY5YiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.expCnsMxBP9wfZab438BOkfl0VPQJftRFG7WPkSRyD0'
                }
            };

            const response = await axios.get(
                `https://api.themoviedb.org/3/movie/popular?page=${page}`,
                options
            );

            const movieList = response.data.results;
            const moviesWithLike = await Promise.all(
                movieList.map(async (movie) => {
                    const likedMovie = await getLikedMovie(movie.id);
                    console.log(likedMovie);
                    return {
                        ...movie,
                        likedMovie: likedMovie
                    };
                })
            );

            setMovies(moviesWithLike);
        } catch (err) {
            console.log(err);
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

    const getLikedMovie = async (idMovie) => {
        try {
            const options = {
                withCredentials: true
            }
            const response = await axios.get(
                `http://localhost:8080/movies/favorite/get?idMovie=${idMovie}`,
                options
            );
            console.log(response.data);
            return response.data;
        } catch (err) {
            console.log(err);
            return null;
        }
    }

    const getLikeImage = (movie) => {
        if (movie.likedMovie === true) {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/liketrue.png';
        } else {
            return 'https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/likefalse.png';
        }
    };

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

    const handleLikeClick = async (idMovie) => {
        await postLiked(idMovie)
        setMovies((prevMovies) =>
            prevMovies.map((movie) => {
                if (movie.id === idMovie) {
                    return {
                        ...movie,
                        likedMovie: !movie.likedMovie,
                    };
                }
                return movie;
            })
        );
    }

    useEffect(() => {
        getMovies(currentPage);
    }, [currentPage]);

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';

    const handlePreviousPage = () => {
        if (parseInt(currentPage) > 1) {
            navigate(`/films/${parseInt(currentPage) - 1}`);
        }
    };

    const handleNextPage = () => {
        if (parseInt(currentPage) < totalPages) {
            navigate(`/films/${parseInt(currentPage) + 1}`);
        }
    };

    return (
        <div className="films-browser-container">
            <h1>Movie Browser</h1>
            <div className="films-browser-list">
                {movies.map(movie => (
                    <div className="film-browser-card" key={movie.id}>
                        <div className="film-browser-poster">
                            <Link to={`/film/${movie.id}`}>
                                <img src={path + movie.poster_path}
                                     className="poster-card"
                                     alt="movie-poster"/>
                            </Link>
                            <div className="film-poster-buttons">
                                <img src="https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/eye.png"
                                     className="watched"
                                     alt="watched"
                                     onClick={() => postWatched(movie.id)}/>
                                <img src={getLikeImage(movie)}
                                     className="like"
                                     alt="like"
                                     onClick={() => handleLikeClick(movie.id)}/>
                                <h1></h1>
                            </div>
                        </div>
                        <div className='film-browser-info'>
                            <div className='film-browser-title'>
                                <Link to={`/film/${movie.id}`}>
                                    <h2>{movie.title}</h2>
                                </Link>
                            </div>
                            <div>{movie.overview}</div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="pagination">
                <button
                    className="pagination-button"
                    onClick={handlePreviousPage}
                    disabled={parseInt(currentPage) === 1}
                >
                    &lt;
                </button>
                <span className="pagination-info">
                    Page {currentPage} of {totalPages}
                </span>
                <button
                    className="pagination-button"
                    onClick={handleNextPage}
                    disabled={parseInt(currentPage) === totalPages}
                >
                    &gt;
                </button>
            </div>
        </div>
    )
}

export default FilmsBrowsingPage;
