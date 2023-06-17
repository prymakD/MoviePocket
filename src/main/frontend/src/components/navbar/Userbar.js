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
                <img
                    src="https://github.com/prymakD/MoviePocket/raw/d36f4f403ed1c15c50b097c93056bbabad50aa87/src/main/frontend/src/images/user.png"
                    alt='USER'/>
            </Link>
        </div>
        )
    )

}

export default Userbar;