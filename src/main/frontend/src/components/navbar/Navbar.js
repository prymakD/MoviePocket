import './Navbar.css'
import Navlist from "./Navlist";
import Userbar from "./Userbar";
import LogoBar from "./LogoBar";
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {checkAuth, getUsernameByAuth} from "../../api/server/UserAPI";

const Navbar = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(null);
    const [userName, setUserName] = useState('');

    const getUserName = async () => {
        try {
            const response = await getUsernameByAuth();
            setUserName(response);
        } catch (error) {
            console.log(error);
        }
    };

    const authenticate = async () => {
        const isAuthenticated = await checkAuth();
        setIsLoggedIn(isAuthenticated);
        await getUserName();
    };

    useEffect(() => {
        authenticate().then();
    }, []);

    return (
        <nav className="Navbar">
            <div className="Container">
                <div className="NavRow">
                    <LogoBar/>
                    <a href="/" className="NavBrand">
                        <strong>MoviePocket</strong>
                    </a>
                    <Navlist/>
                    {isLoggedIn
                        &&
                        <Link to={`/user/${userName}`}>
                            <Userbar/>
                        </Link>
                    }
                </div>
            </div>
        </nav>
    )
}

export default Navbar;