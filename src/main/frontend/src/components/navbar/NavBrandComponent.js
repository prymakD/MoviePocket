import React from 'react';
import './LogoBar.css';
import {Link} from "react-router-dom";
import "./NavBrandComponent.css";

const NavBrandComponent = () => {
    return (
        <Link to ={"/"}>
            <strong className="NavBrand">MoviePocket</strong>
        </Link>
    );
}

export default NavBrandComponent;
