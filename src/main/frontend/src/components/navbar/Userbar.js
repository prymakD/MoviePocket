import './Userbar.css'
import React, {useContext, useState} from "react";
import {Link} from "react-router-dom";
import {UsernameContext} from "../../App";

const Userbar = () => {

    const username = useContext(UsernameContext)
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);

    const handleDropdownToggle = () => {
        setIsDropdownOpen(!isDropdownOpen);
    };

    return(
        <div className='User'>
            <img
                src="https://github.com/prymakD/MoviePocket/raw/d36f4f403ed1c15c50b097c93056bbabad50aa87/src/main/frontend/src/images/user.png"
                alt='USER'
                onClick={handleDropdownToggle}
            />
            {isDropdownOpen && (
                <div className="dropdown-content">
                    <Link to={`/user/${username}`}>My page</Link>
                    <Link to={"/settings"}>Settings</Link>
                </div>
            )}
        </div>
    )

}

export default Userbar;