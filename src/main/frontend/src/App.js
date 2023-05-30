import './App.css';
import './styles/reset.css';
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import {Film} from './pages/Film';
import 'bootstrap/dist/css/bootstrap.min.css'

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path='/' element={<Layout/>}>
                    <Route index element={<Home/>}></Route>
                    <Route path='film' element={<Film/>}></Route>
                    <Route path='film/:title' element={<Film/>}></Route>
                </Route>
            </Routes>
        </div>
    );
}
export default App;
