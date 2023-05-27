import './Navbar.css'
import Navlist from "./Navlist";
import Userbar from "./Userbar";
const Navbar = () => {

    return (
        <nav className="Navbar">
            <div className="Container">
                <div className="NavRow">
                    <a href="/" className="NavBrand">
                        <strong>MoviePocket</strong>
                    </a>
                    <Navlist/>
                    <Userbar/>
                </div>
            </div>
        </nav>
    )
}

export default Navbar;