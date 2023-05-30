import { useParams } from 'react-router-dom'
import {useEffect, useState} from "react";
import axios from "axios";
const Film = () => {
    const {id} = useParams()

    const [movie, setMovie] = useState([]);
    const getMovie = async () => {
        try {
            const response = await axios.get(`https://api.themoviedb.org/3/movie/${id}?api_key=1da35d58fd12497b111e4dd1c4a4c004&language=en-US`);
            console.log(response.data.items[0])
            setMovie(response.data.items[0])
        } catch (err) {
            console.log(err);
        }
    }
    useEffect(() => {
        getMovie().then(() => getMovie());
    }, [])

    const path = 'https://www.themoviedb.org/t/p/w220_and_h330_face';
    return (
        <><h1>{movie.title}</h1>
            <img src={path+movie.poster_path} alt="" />
        </>
    )
}

export {Film};