import './App.css';
import './styles/reset.css';
import 'bootstrap/dist/css/bootstrap.min.css'
import Layout from './components/Layout';
import {Routes, Route} from 'react-router-dom';
import Home from './pages/Home';
import {Film} from './pages/Film';
import RegistrationPage from './pages/RegistrationPage'
import SettingsPage from "./pages/SettingsPage";

function App() {
    return (
        <div className="App">
            <Routes>
                <Route path='/' element={<Layout/>}>
                    <Route index element={<Home/>}></Route>
                    <Route path='registration' element={<RegistrationPage/>}></Route>
                    <Route path='settings' element={<SettingsPage/>}></Route>
                    <Route path='film' element={<Film/>}></Route>
                    <Route path='film/:title' element={<Film/>}></Route>
                </Route>
            </Routes>
        </div>
    );gi
}
export default App;
