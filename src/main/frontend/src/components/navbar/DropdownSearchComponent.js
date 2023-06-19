import React, { useEffect, useState, useRef } from 'react';
import styles from './DropdownSearchComponent.module.css';
import { getMovieSearch } from '../../api/tmdb/MovieAPI';
import {Link} from "react-router-dom";

const DropdownSearchComponent = ({ query }) => {
    const [searchResults, setSearchResults] = useState([]);
    const resultsRef = useRef(null);

    const getSearchList = async () => {
        try {
            const response = await getMovieSearch(query);
            const searchList = response.results;
            setSearchResults(searchList);
        } catch (error) {
            console.log(error);
        }
    };

    useEffect(() => {
        getSearchList(query).then();
    }, [query]);

    useEffect(() => {
        if (resultsRef.current) {
            resultsRef.current.scrollTop = 0;
        }
    }, [searchResults]);

    return (
        <div className={styles.DropdownSearchComponent}>
            <ul ref={resultsRef} className={styles.SearchResults}>
                {searchResults.map((result, index) => (
                    <Link to={`/film/${result.id}`}>
                        <li key={index}>
                            {result.title}
                        </li>
                    </Link>
                ))}
            </ul>
        </div>
    );
};

export default DropdownSearchComponent;
