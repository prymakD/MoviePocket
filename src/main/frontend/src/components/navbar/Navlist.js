import './Navlist.css'
import {Link} from "react-router-dom";

const Navlist = () => {

    return (
        <ul className="Navlist">
            <div className="Search">
                <input type="text" className="Round"/>
                <input type="image" className="Submit"
                       src="https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/search.png"
                       alt="+"/>
            </div>
            <li className="NavListItem"><Link to="/login" className="NavListLink">SIGN IN</Link></li>
            <li className="NavListItem"><Link to="/registration" className="NavListLink">CREATE ACCOUNT</Link></li>
            <li className="NavListItem"><Link to="/films" className="NavListLink">FILMS</Link></li>
            <li className="NavListItem"><Link to="#" className="NavListLink">COMMUNITY</Link></li>
        </ul>
    )
}

export default Navlist;