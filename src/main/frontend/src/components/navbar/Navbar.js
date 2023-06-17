import './Navbar.css'
import Navlist from "./Navlist";
import Userbar from "./Userbar";
import LogoBar from "./LogoBar";
import {Link} from "react-router-dom";
import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../App";
import {getUsernameByAuth} from "../../api/server/UserAPI";

const Navbar = () => {
    const [userName, setUserName] = useState('');
    const isLoggedIn = useContext(AuthContext);

    const getUserName = async () => {
        try {
            const response = await getUsernameByAuth();
            setUserName(response);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getUserName().then();
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