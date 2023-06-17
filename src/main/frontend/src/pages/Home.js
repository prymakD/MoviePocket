import Top from '../components/top/Top';
import {useEffect, useState} from "react";
import {getUpComingMovies} from "../api/tmdb/MovieAPI";

function Home() {

    const [movies, setMovies] = useState([]);
    const getMovies = async () => {
        try {
            const response = await getUpComingMovies();
            setMovies(response)
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getMovies().then();
    }, [])

    return (
        <Top movies = {movies} />
    )
}

export default Home