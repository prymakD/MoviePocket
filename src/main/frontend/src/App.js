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
import {checkAuth, getUsernameByAuth} from "./api/server/UserAPI";
import UserPage from "./pages/UserPage";
import UserWatchedPage from "./pages/UserWatchedPage";
import UserFavoritePage from "./pages/UserFavoritePage";
import NewPasswordPage from "./pages/NewPasswordPage";
import ActivateUserPage from "./pages/ActivateUserPage";
import AboutUsPage from "./pages/AboutUsPage";


import {ToastContainer} from "react-bootstrap";

export const AuthContext = createContext(null)
export const UsernameContext = createContext(null)
const App = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(null);
    const [username, setUsername] = useState(null);

    const authenticate = async () => {
        const isAuthenticated = await checkAuth();
        setIsLoggedIn(isAuthenticated);
        const name = await getUsernameByAuth();
        setUsername(name);
    };

    useEffect(() => {
        authenticate().then();
    }, []);

    if (isLoggedIn === null) {
        // Display loading state or a spinner while checking authentication
        return <div></div>;
    }

    return (
        <div className="App">
            <AuthContext.Provider value={isLoggedIn}>
                <UsernameContext.Provider value={username}>
                    <Routes>
                        <Route path='/' element={<Layout isLogged={isLoggedIn}/>}>
                            <Route index element={<Home/>}></Route>
                            {/* For not logged in */}
                            <Route
                                path='/registration'
                                element={
                                    isLoggedIn ?
                                        (<Navigate to="/" replace/>)
                                        :
                                        (<RegistrationPage/>)
                                }
                            />
                            <Route
                                path='/login'
                                element={
                                    isLoggedIn ?
                                        (<Navigate to="/" replace/>)
                                        :
                                        (<LoginPage/>)
                                }
                            />
                            {/* For logged in */}
                            <Route
                                path='/forgotPassword'
                                element={
                                    <LostPasswordPage/>
                                }
                            />
                            <Route
                                path='/newPassword'
                                element={
                                    <NewPasswordPage/>
                                }
                            />
                            <Route
                                path='/activateUser'
                                element={
                                    <ActivateUserPage/>
                                }
                            />
                            <Route
                                path='/settings'
                                element={
                                    !isLoggedIn ?
                                        (<Navigate to="/" replace/>)
                                        :
                                        (<SettingsPage/>)
                                }
                            />
                            {/* For everyone */}
                            <Route
                                path='films/:currentPage'
                                element={<FilmsBrowsingPage/>}
                            />
                            <Route
                                path='/aboutUs'
                                element={<AboutUsPage/>}
                            />
                            <Route
                                path='film/:id'
                                element={<FilmPage/>}
                            />
                            <Route
                                path='user/:username'
                                element={<UserPage/>}
                            />
                            <Route
                                path='user/:username/favorite'
                                element={<UserFavoritePage/>}
                            />
                            <Route
                                path='user/:username/watched'
                                element={<UserWatchedPage/>}
                            />
                        </Route>
                    </Routes>
                </UsernameContext.Provider>
            </AuthContext.Provider>
            <ToastContainer/>
        </div>
    );
}
export default App;
