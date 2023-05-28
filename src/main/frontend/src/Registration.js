import {Route, Routes} from "react-router-dom";
import Layout from "./components/Layout";
import React from "react";

function Registration() {
    return (
        <div className="Registration">
            <Routes>
                <Route path='/' element={<Layout/>}>

                </Route>
            </Routes>
        </div>
    );
}

export default Registration;