import React from 'react';
import {postLogout} from "../../api/server/AuthenticationAPI";
import "./LogoutComponent.css"

const LogoutComponent = () => {
    const handleLogout = async () => {
        try {
            await postLogout();
            document.cookie = 'authToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
            window.location.href = '/'
        } catch (error) {
            console.log(error);
        }
    };

    return (
        <span onClick={handleLogout}>LogOut</span>
    );
};

export default LogoutComponent;
