import React from 'react';
import { Link } from 'react-router-dom';
import * as Icon from 'react-bootstrap-icons';
import '../Styles/navbar.css';

const Navbar = () => {
    return (
        <nav className="navbar">
            <div className="navbar-container">
                {/* <Link to="/" className="navbar-logo">
                    BookExchange
                </Link> */}
                <div className="navbar-links">
                <Link to="/" className="nav-link">Home</Link>
                <Link to="/books" className="nav-link">My Books</Link>
                <Link to="/Inbox" className="icon">
                    <Icon.Inbox/>
                </Link>
                <Link to="/Profile" className="icon">
                    <Icon.PersonCircle/>
                </Link>

                </div>
            </div>
        </nav>
    );
};

export default Navbar;