import './App.css';
import './styles/reset.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import {FilmPage} from './pages/FilmPage';
import RegistrationPage from './pages/RegistrationPage';
import SettingsPage from "./pages/SettingsPage";
import FilmsBrowsingPage from "./pages/FilmsBrowsingPage";
import LoginPage from "./pages/LoginPage";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path='/' element={<Layout/>}>
                    <Route index element={<Home/>}></Route>
                    <Route path='registration' element={<RegistrationPage/>}></Route>
                    <Route path='login' element={<LoginPage/>}></Route>
                    <Route path='settings' element={<SettingsPage/>}></Route>
                    <Route path='films/:currentPage' element={<FilmsBrowsingPage/>}></Route>
                    <Route path='film/:id' element={<FilmPage/>}></Route>
                </Route>
            </Routes>
        </div>
    );
}
export default App;
