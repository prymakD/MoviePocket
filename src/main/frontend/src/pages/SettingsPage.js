import React, { useState } from 'react';
import axios from 'axios';
import './SettingsPage.css';

const SettingsPage = () => {
    const [bio, setBio] = useState('');
    const [newEmail, setNewEmail] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [newUsername, setNewUsername] = useState('');

    const handleBioChange = (event) => {
        setBio(event.target.value);
    };

    const handleNewEmailChange = (event) => {
        setNewEmail(event.target.value);
    };

    const handleNewPasswordChange = (event) => {
        setNewPassword(event.target.value);
    };

    const handleNewUsernameChange = (event) => {
        setNewUsername(event.target.value);
    };

    const handleAccountDeletion = async () => {

        try {
            const response = await axios.delete('http://localhost:8080/account');
            console.log('Account deletion successful!', response.data);
            // Дополнительные действия после удаления аккаунта...
        } catch (error) {
            console.error('Error occurred during account deletion:', error);
        }
    };

    const handleSettingsUpdate = async (event) => {
        event.preventDefault();

        // Формирование данных для обновления настроек
        const updatedSettings = {};
        if (bio) {
            updatedSettings.bio = bio;
        }
        if (newEmail) {
            updatedSettings.email = newEmail;
        }
        if (newPassword) {
            updatedSettings.password = newPassword;
        }
        if (newUsername) {
            updatedSettings.username = newUsername;
        }

        // Отправить запрос на обновление настроек пользователя
        try {
            const response = await axios.patch('http://localhost:8080/settings', updatedSettings);
            console.log('Settings update successful!', response.data);
            // Дополнительные действия после обновления настроек...
        } catch (error) {
            console.error('Error occurred during settings update:', error);
        }
    };

    return (
        <div className="settings-container">
            <h1>Settings</h1>
            <form onSubmit={handleSettingsUpdate}>
                <div>
                    <label htmlFor="bio">Biography</label>
                    <br />
                    <textarea id="bio" value={bio} onChange={handleBioChange} />
                </div>
                <div>
                    <label htmlFor="newEmail">New Email:</label>
                    <br />
                    <input
                        type="email"
                        id="newEmail"
                        value={newEmail}
                        onChange={handleNewEmailChange}
                    />
                </div>
                <div>
                    <label htmlFor="newPassword">New Password:</label>
                    <br />
                    <input
                        type="password"
                        id="newPassword"
                        value={newPassword}
                        onChange={handleNewPasswordChange}
                    />
                </div>
                <div>
                    <label htmlFor="newUsername">New Username:</label>
                    <br />
                    <input
                        type="text"
                        id="newUsername"
                        value={newUsername}
                        onChange={handleNewUsernameChange}
                    />
                </div>
                <button type="submit">Save Changes</button>
            </form>
            <button onClick={handleAccountDeletion}>Delete Account</button>
        </div>
    );
};

export default SettingsPage;
