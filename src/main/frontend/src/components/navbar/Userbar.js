import './Userbar.css'
import {Link} from "react-router-dom";
import {useEffect, useState} from "react";
import {checkAuth} from "../../api/server/UserAPI";

const Userbar= () => {
    const [isLoggedIn, setIsLoggedIn] = useState(null);

    useEffect(() => {
        const authenticate = async () => {
            const isAuthenticated = await checkAuth();
            setIsLoggedIn(isAuthenticated);
        };

        authenticate().then();
    }, []);

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