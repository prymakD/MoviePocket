import './Navbar.css'
import Navlist from "./Navlist";
import Userbar from "./Userbar";
import LogoBar from "./LogoBar";
import {Link} from "react-router-dom";

const Navbar = () => {
    return (
        <nav className="Navbar">
            <div className="Container">
                <div className="NavRow">
                    <LogoBar/>
                    <a href="/" className="NavBrand">
                        <strong>MoviePocket</strong>
                    </a>
                    <Navlist/>
                    <Link to="/settings">
                        <Userbar/>
                    </Link>
                </div>
            </div>
        </nav>
    )
}

export default Navbar;