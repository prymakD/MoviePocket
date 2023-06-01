import './Navlist.css'
import {Link} from "react-router-dom";
const Navlist = () => {

    return (
        <ul className="Navlist">
            <div className="Search">
                <input type="text" className="Round"/>
                <input type="submit" className="Submit" value="+"/>
            </div>
            <li className="NavListItem"><Link to="#" className="NavListLink">SIGN IN</Link></li>
            <li className="NavListItem"><Link to="#" className="NavListLink">CREATE ACCOUNT</Link></li>
            <li className="NavListItem"><Link to="/film" className="NavListLink">FILMS</Link></li>
            <li className="NavListItem"><Link to="#" className="NavListLink">COMMUNITY</Link></li>
        </ul>

    )
}

export default Navlist;