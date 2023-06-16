import React, {useState} from 'react';
import axios from 'axios';
import './RegistrationPage.css';
import queryString from "query-string";

const LostPasswordPage = () => {
    const [email, setEmail] = useState('');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const params = {
            email: email
        }

        try {
            const response = await axios.post(
                'http://localhost:8080/lostpassword/',
                queryString.stringify(params)
            );

            console.log('Password reset email sent!', response.data);
            // Additional logic here for displaying success message or redirecting to a different page
        } catch (error) {
            console.error('Error occurred while sending password reset email:', error);
            // Additional logic here for displaying error message
        }
    };

    return (
        <div className="lost-password-container">
            <h1>Forgot Password</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="email">Provide your email</label>
                    <br/>
                    <input
                        className="registration-input"
                        type="email"
                        id="email"
                        value={email}
                        onChange={handleEmailChange}
                    />
                </div>
                <button className="lost-password-button" type="submit">
                    Reset Password
                </button>
            </form>
        </div>
    );
};

export default LostPasswordPage;
