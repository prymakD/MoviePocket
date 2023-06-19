import React, {useEffect, useState} from 'react';
import './SettingsPage.css';
import {
    deleteUser,
    getUserDto,
    postNewBio,
    postNewEmail,
    postNewPassword,
    postNewUsername
} from "../api/server/SettingAPI";
import {getRandomMovie} from "../api/tmdb/MovieAPI";

const SettingsPage = () => {
    const [settings, setSettings] = useState('');
    const [bio, setBio] = useState('');
    const [newEmail, setNewEmail] = useState('');
    const [newPassword0, setNewPassword0] = useState('');
    const [newPassword1, setNewPassword1] = useState('');
    const [passwordOld, setPasswordOld] = useState('');
    const [newUsername, setNewUsername] = useState('');
    const [backgroundImage, setBackgroundImage] = useState('');

    const getUserSettings = async () => {
        try {
            const response = await getUserDto();
            setSettings(response);
        } catch (error) {
            console.log(error);
        }
    };

    const handleBioChange = (event) => {
        setBio(event.target.value);
    };

    const handleEmailChange = (event) => {
        setNewEmail(event.target.value);
    };

    const handlePassword0Change = (event) => {
        setNewPassword0(event.target.value);
    };

    const handlePassword1Change = (event) => {
        setNewPassword1(event.target.value);
    };

    const handlePasswordOldChange = (event) => {
        setPasswordOld(event.target.value);
    };

    const handleUsernameChange = (event) => {
        setNewUsername(event.target.value);
    };

    const handleUpdateBio = async () => {
        try {
            const response = await postNewBio(bio);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdateEmail = async () => {
        try {
            const response = await postNewEmail(newEmail);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdatePassword = async () => {
        try {
            const response = await postNewPassword(newPassword0, newPassword1, passwordOld);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdateUsername = async () => {
        try {
            const response = await postNewUsername(newUsername);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleAccountDeletion = async () => {

        try {
            const response = await deleteUser(password);
            console.log('Account deletion successful!', response.data);
        } catch (error) {
            console.error('Error occurred during account deletion:', error);
        }
    };

    useEffect(() => {
        getRandomMovieImage().then();
        getUserSettings().then();
    }, []);

    const getRandomMovieImage = async () => {
        try {
            const response = await getRandomMovie();
            setBackgroundImage(`https://image.tmdb.org/t/p/original${response.backdrop_path}`);
        } catch (error) {
            console.log(error);
        }
    };

    return (
        // <div className="user-page-container"
        //      style={{backgroundImage: `url(${backgroundImage})`}}>
        <div className="settings-container">
            <h1 className="white-text">Settings</h1>
            <div className="edit-container">
                <div className="username-info">
                    <label className="blue-text" htmlFor="username">Username: </label>
                    <span className="yellow-text">{settings.username}</span>
                </div>
                <input
                    className="input-text0"
                    type="text"
                    id="newUsername"
                    placeholder="New username"
                    value={newUsername}
                    onChange={handleUsernameChange}
                />
                <button className="button-update" onClick={handleUpdateUsername}>Update</button>
            </div>
            <div className="edit-container">
                <div className="username-info">
                    <label className="blue-text" htmlFor="email">Email:</label>
                    <span className="yellow-text">{settings.email}</span>
                </div>
                <input
                    className="input-text0"
                    placeholder="New Email"
                    type="email"
                    id="newEmail"
                    value={newEmail}
                    onChange={handleEmailChange}
                />
                <button className="button-update" onClick={handleUpdateEmail}>update</button>
            </div>
            <div className="edit-container">
                <div className="username-info">
                    <label className="blue-text" htmlFor="bio">Biography:</label>
                    <span className="yellow-text">{settings.bio}</span>
                </div>
                <textarea className="input-text1" id="newBio" value={bio} onChange={handleBioChange}/>
                <button className="button-update" onClick={handleUpdateBio}>update</button>
            </div>
            <div className="password-container">
                <div>
                    <label className="blue-text" htmlFor="newPasswordOld">Old Password:</label>
                    <br/>
                    <input
                        className="input-text0"
                        type="password"
                        id="newPasswordOld"
                        value={passwordOld}
                        onChange={handlePasswordOldChange}
                    />
                </div>
                <div>
                    <div>
                        <label className="blue-text" htmlFor="newPassword0">New Password:</label>
                        <br/>
                        <input
                            className="input-text0"
                            type="password"
                            id="newPassword0"
                            value={newPassword0}
                            onChange={handlePassword0Change}
                        />
                    </div>
                    <div>
                        <label className="blue-text" htmlFor="newPassword1">Confirm New Password:</label>
                        <br/>
                        <input
                            className="input-text0"
                            type="password"
                            id="newPassword1"
                            value={newPassword1}
                            onChange={handlePassword1Change}
                        />
                    </div>
                </div>
                <button className="button-update" onClick={handleUpdatePassword}>Update</button>
            </div>
            <div className="edit-container">
                <div>
                    <label className="blue-text" htmlFor="newPassword1">Password:</label>
                    <br/>
                    <input
                        className="input-text0"
                        type="password0"
                        id="Password0"
                        onChange={handlePassword1Change}
                    />
                </div>
                <button className="button-update" onClick={handleAccountDeletion}>Delete Account</button>
            </div>
        </div>
        // </div>
    );
};

export default SettingsPage;
