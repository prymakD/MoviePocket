import Top from '../components/top/Top';
import {useEffect, useState} from "react";
import axios from "axios";

function Home() {

    const [movies, setMovies] = useState([]);
    const getMovies = async () => {
        try {
            const response = await axios.get('https://api.themoviedb.org/3/list/1?api_key=1da35d58fd12497b111e4dd1c4a4c004&language=en-US');
            console.log(response.data.items[0])
            setMovies(response.data.items)
        } catch (err) {
            console.log(err);
        }
    }

    useEffect(() => {
        getMovies();
    }, [])

    return (
        <Top movies = {movies} />
    )
}

export default Home