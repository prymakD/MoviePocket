import './Navlist.css'
import {Link} from "react-router-dom";
const Navlist = () => {

    return (
        <ul className="Navlist">
            <div className="Search">
                <input type="text" className="Round"/>
                <input type="image" className="Submit" src="https://cdn-icons-png.flaticon.com/512/751/751463.png" alt="+"/>
            </div>
            <li className="NavListItem"><Link to="#" className="NavListLink">SIGN IN</Link></li>
            <li className="NavListItem"><Link to="/registration" className="NavListLink">CREATE ACCOUNT</Link></li>
            <li className="NavListItem"><Link to="/films" className="NavListLink">FILMS</Link></li>
            <li className="NavListItem"><Link to="#" className="NavListLink">COMMUNITY</Link></li>
        </ul>
    )
}

export default Navlist;