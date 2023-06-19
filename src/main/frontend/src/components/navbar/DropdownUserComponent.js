import {Link} from "react-router-dom";
import React, {useContext} from "react";
import "./DropdownUserComponent.css"
import {UsernameContext} from "../../App";
import LogoutComponent from "./LogoutComponent";

const DropdownUserComponent = () => {
    const username = useContext(UsernameContext)

    return(
        <div className="dropdown-content">
            <Link to={`/user/${username}`}>My page</Link>
            <Link to={"/settings"}>Settings</Link>
            <LogoutComponent/>
        </div>
    )
}

export default DropdownUserComponent