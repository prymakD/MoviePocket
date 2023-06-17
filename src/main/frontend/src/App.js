import './App.css';
import './styles/reset.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Layout from './components/Layout';
import {Navigate, Route, Routes} from 'react-router-dom';
import Home from './pages/Home';
import {FilmPage} from './pages/FilmPage';
import RegistrationPage from './pages/RegistrationPage';
import SettingsPage from "./pages/SettingsPage";
import FilmsBrowsingPage from "./pages/FilmsBrowsingPage";
import LoginPage from "./pages/LoginPage";
import LostPasswordPage from "./pages/LostPasswordPage"
import {createContext, useEffect, useState} from "react";
import {checkAuth} from "./api/server/UserAPI";
import UserPage from "./pages/UserPage";


export const AuthContext = createContext(null)
const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(null);

    useEffect(() => {
        const authenticate = async () => {
            const isAuthenticated = await checkAuth();
            setIsLoggedIn(isAuthenticated);
        };

        authenticate().then();
    }, []);

    if (isLoggedIn === null) {
        // Display loading state or a spinner while checking authentication
        return <div></div>;
    }

    return (
        <div className="App">
            <AuthContext.Provider value={isLoggedIn}>
                <Routes>
                    <Route path='/' element={<Layout isLogged={isLoggedIn}/>}>
                        <Route index element={<Home />}></Route>
                        {/* For not logged in */}
                        <Route
                            path='/registration'
                            element={
                                isLoggedIn ?
                                    (<Navigate to="/" replace/>)
                                    :
                                    (<RegistrationPage />)
                                }
                        />
                        <Route
                            path='/login'
                            element={
                                isLoggedIn ?
                                    (<Navigate to="/" replace/>)
                                    :
                                    (<LoginPage />)
                                }
                        />
                        {/* For logged in */}
                        <Route
                            path='/forgotPassword'
                            element={
                                !isLoggedIn ?
                                    (<Navigate to="/" replace/>)
                                    :
                                    (<LostPasswordPage />)
                                }
                        />
                        <Route
                            path='/settings'
                            element={
                                !isLoggedIn ?
                                    (<Navigate to="/" replace/>)
                                    :
                                    (<SettingsPage />)
                                }
                        />
                        {/* For everyone */}
                        <Route
                            path='films/:currentPage'
                            element={<FilmsBrowsingPage />}
                        />
                        <Route
                            path='film/:id'
                            element={<FilmPage />}
                        />
                        <Route
                            path='user/:username'
                            element={<UserPage />}
                        />
                    </Route>
                </Routes>
            </AuthContext.Provider>
        </div>
    );
}
export default App;
