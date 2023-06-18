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

const SettingsPage = () => {
    const [settings, setSettings] = useState('');
    const [bio, setBio] = useState('');
    const [newEmail, setNewEmail] = useState('');
    const [newPassword0, setNewPassword0] = useState('');
    const [newPassword1, setNewPassword1] = useState('');
    const [newPasswordOld, setNewPasswordOld] = useState('');
    const [newUsername, setNewUsername] = useState('');

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
        setNewPasswordOld(event.target.value);
    };

    const handleUsernameChange = (event) => {
        setNewUsername(event.target.value);
    };

    const handleUpdateBio = async (bio) => {
        try {
            const response = await postNewBio(bio);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdateEmail = async (newEmail) => {
        try {
            const response = await postNewEmail(newEmail);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdatePassword = async (password0, password1, passwordOld) => {
        try {
            const response = await postNewPassword(password0, password1, passwordOld);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdateUsername = async (newUsername) => {
        try {
            const response = await postNewUsername(newUsername);
            getUserSettings().then()
        } catch (err) {
            console.error(err);
        }
    };

    const handleAccountDeletion = async (password) => {

        try {
            const response = await deleteUser(password);
            console.log('Account deletion successful!', response.data);
        } catch (error) {
            console.error('Error occurred during account deletion:', error);
        }
    };

    useEffect(() => {
        getUserSettings().then();
    }, []);

    return (
        <div className="settings-container">
            <h1>Settings</h1>
            <div>
                <label htmlFor="username">Username:</label>
                <span>{settings.username}</span>
            </div>
            <div>
                <label htmlFor="email">Email:</label>
                <span>{settings.email}</span>
            </div>
            <div>
                <label htmlFor="bio">Biography:</label>
                <span>{settings.bio}</span>
            </div>
            <div>
                <label htmlFor="newBio">New Bio:</label>
                <br />
                <textarea id="newBio" value={bio} onChange={handleBioChange} />
                <button onClick={handleUpdateBio}>Update Bio</button>
            </div>
            <div>
                <label htmlFor="newEmail">New Email:</label>
                <br />
                <input
                    type="email"
                    id="newEmail"
                    value={newEmail}
                    onChange={handleEmailChange}
                />
                <button onClick={handleUpdateEmail}>Update Email</button>
            </div>
            <div>
                <label htmlFor="newPassword0">New Password:</label>
                <br />
                <input
                    type="password"
                    id="newPassword0"
                    value={newPassword0}
                    onChange={handlePassword0Change}
                />
            </div>
            <div>
                <label htmlFor="newPassword1">Confirm New Password:</label>
                <br />
                <input
                    type="password"
                    id="newPassword1"
                    value={newPassword1}
                    onChange={handlePassword1Change}
                />
            </div>
            <div>
                <label htmlFor="newPasswordOld">Old Password:</label>
                <br />
                <input
                    type="password"
                    id="newPasswordOld"
                    value={newPasswordOld}
                    onChange={handlePasswordOldChange}
                />
            </div>
            <button onClick={handleUpdatePassword}>Update Password</button>
            <div>
                <label htmlFor="newUsername">New Username:</label>
                <br />
                <input
                    type="text"
                    id="newUsername"
                    value={newUsername}
                    onChange={handleUsernameChange}
                />
                <button onClick={handleUpdateUsername}>Update Username</button>
            </div>
            <button onClick={handleAccountDeletion}>Delete Account</button>
        </div>
    );
};

export default SettingsPage;
