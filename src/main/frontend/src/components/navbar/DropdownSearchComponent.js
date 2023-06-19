import React, {useEffect, useState} from 'react';
import './DropdownSearchComponent.css';
import {getMovieInfoSearch} from "../../api/server/SearchAPI";

const DropdownSearchComponent = ({ query }) => {
    const [searchResults, setSearchResults] = useState([]);

    const getSearchList = async () => {
        try {
            const searchList = await getMovieInfoSearch(query)
            if (searchList.length > 6){
                setSearchResults(searchList.slice(0,5));
            } else {
                setSearchResults(searchList)
            }
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getSearchList(query).then();
    }, []);

    return (
        <div className="DropdownSearchComponent">
            <ul className="SearchResults">
                {searchResults.map((result, index) => (
                    <li key={index}>{result.title}</li>
                ))}
            </ul>
        </div>
    );
};
export default DropdownSearchComponent;
