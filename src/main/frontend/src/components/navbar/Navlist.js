import './Navlist.css'
import {Link} from "react-router-dom";
import {useContext} from "react";
import {AuthContext} from "../../App";
import Userbar from "./Userbar";
import {postLogout} from "../../api/server/AuthenticationAPI";

const Navlist = () => {
    const isLoggedIn = useContext(AuthContext);

    const handleLogout = async () => {
        try {
            await postLogout();
            document.cookie = 'authToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
            window.location.href = '/'
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <ul className="Navlist">
            <div className="Search">
                <input type="text" className="Round"/>
                <input type="image" className="Submit"
                       src="https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/search.png"
                       alt="+"/>
            </div>
            {!isLoggedIn
                &&
                (<li className="NavListItem"><Link to="/login" className="NavListLink">SIGN IN</Link></li>)
            }
            {!isLoggedIn
                &&
                (<li className="NavListItem"><Link to="/registration" className="NavListLink">CREATE ACCOUNT</Link></li>)
            }
            <li className="NavListItem"><Link to="/films/1" className="NavListLink">FILMS</Link></li>
            <li className="NavListItem"><Link to="#" className="NavListLink">COMMUNITY</Link></li>
            {isLoggedIn
                &&
                <Link to={`/settings`}>
                    <Userbar/>
                </Link>
            }
            {isLoggedIn
                &&
                <span onClick={handleLogout}>Log Out</span>
            }
        </ul>
    )
}

export default Navlist;