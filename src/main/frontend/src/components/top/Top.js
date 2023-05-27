import './Top.css';
import Carousel from 'react-material-ui-carousel';
import {Paper} from '@mui/material';
import {Link} from "react-router-dom";
const Top = ({movies}) => {
    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';
    const temp = 'https://alienhive.pl/wp-content/uploads/2020/09/film-diuna-2020-dune.jpg'
    return (
        <div className='movie-carousel-container'>
            <Carousel>
                {
                    movies.map((movie) =>{
                        return(
                            <Paper>
                                <div className='movie-card-container'>
                                    <div className='movie-card' style={{"--img": `url(${temp})`}}>
                                        <div className='movie-detail'>
                                            <div className='movie-title'>
                                                <Link key={movie.id} to={`/film/${movie.title}`}>Test</Link>
                                                <h1>{movie.title}</h1>
                                            </div>
                                            <div className='movie-description'>
                                                <h4>{movie.overview}</h4>
                                            </div>
                                        </div>
                                        <div className='movie-poster-carousel'>
                                            <div className='movie-poster'>
                                                <img src={path+movie.poster_path} alt="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </Paper>
                        )
                    })
                }
            </Carousel>
        </div>
    )
}

export default Top