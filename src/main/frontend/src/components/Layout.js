import { Outlet } from 'react-router-dom';

import React from 'react'
import Navbar from "./navbar/Navbar";
import variables from '../styles/styles.module.css'; // DON'T DELETE

const Layout = () => {
    return(
        <>
            <Navbar/>
            <Outlet/>
        </>
    )
}

export default Layout