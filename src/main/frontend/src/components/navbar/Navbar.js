import './Navbar.css'
import Navlist from "./Navlist";
import LogoBar from "./LogoBar";
import {useContext} from "react";
import {AuthContext, UsernameContext} from "../../App";
import Userbar from "./Userbar";
import NavBrandComponent from "./NavBrandComponent";

const Navbar = () => {
    const isLoggedIn = useContext(AuthContext);
    const username = useContext(UsernameContext);
    return (
        <nav className="Navbar">
            <div className="NavRow">
                <div className="LeftContainer">
                    <LogoBar/>
                    <NavBrandComponent/>
                </div>
                <Navlist/>
                <div className="RightContainer">
                    {isLoggedIn
                        &&
                        <Userbar/>
                    }
                    {isLoggedIn
                        &&
                        <div className="Username">{username}</div>
                    }
                </div>
            </div>
        </nav>
    )
}

export default Navbar;