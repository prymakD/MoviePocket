import './Top.css';
import Carousel from 'react-material-ui-carousel';
import {Link} from "react-router-dom";

const Top = ({movies}) => {
    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';
    //const temp = 'https://alienhive.pl/wp-content/uploads/2020/09/film-diuna-2020-dune.jpg'
    return (
        <div className='movie-carousel-container'>
            <Carousel indicators={false}>
                {
                    movies.map((movie) =>{
                        return(
                            <div className='main-container'>
                                <div key={movie.id} className='movie-card'
                                     style={{backgroundImage: `url(${path + movie.poster_path})`}}>
                                    <div className='movie-detail'>
                                        <div className='movie-poster'>
                                            <Link to={`/film/${movie.id}`}>
                                                <img src={path + movie.poster_path} alt="Movie poster"/>
                                            </Link>
                                        </div>
                                        <div className='movie-title'>
                                            <Link to={`/film/${movie.id}`}>
                                                <h1>{movie.title}</h1>
                                            </Link>
                                        </div>
                                        <div className='movie-description'>
                                            <h4>{movie.overview}</h4>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        )
                    })
                }
            </Carousel>
        </div>
    )
}

export default Top