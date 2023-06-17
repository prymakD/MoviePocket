import { Outlet } from 'react-router-dom';

import React from 'react'
import Navbar from "./navbar/Navbar";

const Layout = (isLoggedIn) => {
    return(
        <>
            <Navbar isLoggedIn={isLoggedIn}/>
            <Outlet/>
        </>
    )
}

export default Layout