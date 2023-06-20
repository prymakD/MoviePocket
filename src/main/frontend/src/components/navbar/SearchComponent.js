import './SearchComponent.css'
import {useState} from "react";
import DropdownSearchComponent from "./DropdownSearchComponent";

const SearchComponent = () => {
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const [searchText, setSearchText] = useState('');

    const handleDropdownToggle = () => {
        setIsDropdownOpen(!isDropdownOpen);
    };

    const handleSearchTextChange = (event) => {
        setSearchText(event.target.value);
    };

    return (
        <div className="Search">
            <input type="text"
                   className="Round"
                   value={searchText}
                   onChange={handleSearchTextChange}
            />
            <input type="image"
                   className="Submit"
                   src="https://raw.githubusercontent.com/prymakD/MoviePocket/1458e6c307d0ae5d381bd8607aa7758ccef1a575/src/main/frontend/src/images/search.png"
                   onClick={handleDropdownToggle}
                   alt="+"
            />
            {isDropdownOpen
                &&
                <DropdownSearchComponent
                query={searchText}/>
            }
        </div>
    )
}

export default SearchComponent;