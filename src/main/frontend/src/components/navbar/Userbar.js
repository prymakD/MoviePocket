import './Userbar.css'
import React, {useState} from "react";
import DropdownUserComponent from "./DropdownUserComponent";

const Userbar = () => {
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
                <DropdownUserComponent/>
            )}
        </div>
    )

}

export default Userbar;