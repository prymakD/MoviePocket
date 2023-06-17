import './Userbar.css'
import {Link} from "react-router-dom";

const Userbar= (isLoggedIn) => {
    return(
        (isLoggedIn &&
        <div className='User'>
            <Link to="/settings">
                <img src="https://cdn.icon-icons.com/icons2/1154/PNG/512/1486564400-account_81513.png" alt='USER'/>
            </Link>
        </div>
        )
    )

}

export default Userbar;