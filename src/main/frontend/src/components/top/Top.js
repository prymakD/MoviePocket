import './Top.css';
import Carousel from 'react-material-ui-carousel';
import {Paper} from '@mui/material';

const Top = ({movies}) => {
    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';
    return (
        <div className='movie-carousel-container'>
            <Carousel>
                {
                    movies.map((movie) =>{
                        return(
                            <Paper>
                                <div className='movie-card-container'>
                                    <div className='movie-card'>
                                        <div className='movie-poster'>
                                            <img src={path+movie.poster_path} alt="" />
                                        </div>
                                        <div className='movie-detail'>
                                            <div className='movie-title'>
                                                <h4>{movie.title}</h4>
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