import './App.css';
import axios from 'axios';
import React, {useEffect, useState} from 'react';
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './components/home/Home';
import 'bootstrap/dist/css/bootstrap.min.css'
function App() {
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
        <div className="App">
            <Routes>
                <Route path='/' element={<Layout/>}>
                    <Route path='/' element={<Home movies={movies}/>}></Route>
                </Route>
            </Routes>
        </div>
    );
}
export default App;
