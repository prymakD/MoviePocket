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
import {toast, ToastContainer} from "react-toastify";
import {postLogout} from "../api/server/AuthenticationAPI";


const SettingsPage = () => {
    const [settings, setSettings] = useState('');
    const [bio, setBio] = useState('');
    const [newEmail, setNewEmail] = useState('');
    const [newPassword0, setNewPassword0] = useState('');
    const [newPassword1, setNewPassword1] = useState('');
    const [passwordOld, setPasswordOld] = useState('');
    const [newUsername, setNewUsername] = useState('');
    const [passwordDeletion, setPasswordDeletion] = useState('');
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

    const handlePasswordDeletionChange = (event) => {
        setPasswordDeletion(event.target.value);
    };

    const handleUsernameChange = (event) => {
        setNewUsername(event.target.value);
    };

    const handleUpdateBio = async () => {
        try {
            const response = await postNewBio(bio);
            if (!response) {
                toast.error('Something went wrong! \n Please try again)', {
                    position: toast.POSITION.TOP_CENTER,
                });
            } else if (response) {
                getUserSettings().then()
                toast.success('Bio is successfully changed)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                setBio('');
            }
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdateEmail = async () => {
        try {
            const response = await postNewEmail(newEmail);

            if (!response) {
                toast.error('Something went wrong! \n Please try again)', {
                    position: toast.POSITION.TOP_CENTER,
                });
            } else if (response) {
                getUserSettings().then()
                toast.success('Email is successfully changed)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                setNewEmail('');
            }
        } catch (err) {
            console.error(err);
        }
    };

    const handleUpdatePassword = async () => {
        try {
            const response = await postNewPassword(newPassword0, newPassword1, passwordOld);

            if (!response) {
                toast.error('Something went wrong! \n Please try again)', {
                    position: toast.POSITION.TOP_CENTER,
                });
            } else if (response) {
                getUserSettings().then()
                toast.success('Password is successfully changed)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                setNewPassword0('');
                setNewPassword1('');
                setPasswordOld('');

            }
        } catch (err) {

            console.error(err);
        }
    };

    const handleUpdateUsername = async () => {
        try {
            const response = await postNewUsername(newUsername);
            if (!response) {
                toast.error('Something went wrong! \n Please try again)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                console.error("Something wrong");
            } else if (response) {
                getUserSettings().then()
                toast.success('Username is successfully changed)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                setNewUsername('')
            }
        } catch (err) {
            console.error(err);
        }
    };

    const handleAccountDeletion = async () => {

        try {
            const response = await deleteUser(passwordDeletion);
            if (!response) {
                toast.error('Something went wrong! \n Please try again)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                console.error("Something wrong");
            } else if (response) {
                getUserSettings().then()
                toast.success('User is successfully deleted)', {
                    position: toast.POSITION.TOP_CENTER,
                });
                await postLogout();
                window.location.href = '/'

            }
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
        <div className="settings-page-container"
             style={{backgroundImage: `url(${backgroundImage})`, backgroundAttachment: 'fixed'}}>
            <div className="main">
                <div className="settings-container">
                    <h1 className="white-text">Settings</h1>
                    <div className="edit-container">
                        <div className="username-info">
                            <label className="blue-text" htmlFor="username">Username: </label>
                            <span className="yellow-text">{settings.username}</span>
                        </div>
                        <div className="input-button">
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
                    </div>
                    <div className="edit-container">
                        <div className="username-info">
                            <label className="blue-text" htmlFor="email">Email:</label>
                            <span className="yellow-text">{settings.email}</span>
                        </div>
                        <div className="input-button">
                            <input
                                className="input-text0"
                                placeholder="New Email"
                                type="email"
                                id="newEmail"
                                value={newEmail}
                                onChange={handleEmailChange}
                            />
                            <button className="button-update" onClick={handleUpdateEmail}>Update</button>
                        </div>
                    </div>
                    <div className="edit-container">
                        <div className="username-info">
                            <label className="blue-text" htmlFor="bio">Biography:</label>
                            <span className="yellow-text">{settings.bio}</span>
                        </div>
                        <div className="input-button">
                            <textarea className="input-text1" id="newBio" value={bio} onChange={handleBioChange}/>
                            <button className="button-update" onClick={handleUpdateBio}>Update</button>
                        </div>
                    </div>
                    <div className="password-container">
                        <label className="blue-text" htmlFor="newPasswordOld">New Password:</label>
                        <div>
                            <input
                                className="input-text0"
                                placeholder="Old Password"
                                type="password"
                                id="newPasswordOld"
                                value={passwordOld}
                                onChange={handlePasswordOldChange}
                            />
                        </div>
                        <div>
                            <br/>
                            <input
                                className="input-text0"
                                placeholder="New Password"
                                type="password"
                                id="newPassword0"
                                value={newPassword0}
                                onChange={handlePassword0Change}
                            />
                        </div>
                        <br/>
                        <div className="input-button">

                            <input
                                className="input-text0"
                                placeholder="Confirm New Password"
                                type="password"
                                id="newPassword1"
                                value={newPassword1}
                                onChange={handlePassword1Change}
                            />
                            <button className="button-update" onClick={handleUpdatePassword}>Update</button>
                        </div>
                    </div>
                    <div className="edit-container">
                        <label className="blue-text" htmlFor="passwordDeletion">Delete Account:</label>
                        <div className="input-button">
                            <input
                                className="input-text0"
                                placeholder="Password"
                                type="password"
                                id="passwordDeletion"
                                onChange={handlePasswordDeletionChange}
                            />
                            <button className="button-update" onClick={handleAccountDeletion}>Delete</button>
                        </div>
                    </div>
                </div>
            </div>
            <ToastContainer/>
        </div>
    );
};

export default SettingsPage;
