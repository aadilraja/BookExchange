import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import * as Icon from 'react-bootstrap-icons';
import decor from '../assets/decor.png';
import '../Styles/navbar.css';

const Navbar = () => {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

    const toggleMobileMenu = () => {
        setMobileMenuOpen(!mobileMenuOpen);
    };

    return (
        <nav className="navbar">
            <div className="logo-container">
                <Link to="/" className='logo-Link'>
                    <h1 className="logo">Your Logo</h1>
                </Link>
            </div>
            
            <div className="mobile-menu-toggle" onClick={toggleMobileMenu}>
                {mobileMenuOpen ? <Icon.X size={24} /> : <Icon.List size={24} />}
            </div>
            
            <div className={`links-container ${mobileMenuOpen ? 'mobile-active' : ''}`}>
                <div className="text-links">
                    <Link to="/" className="link">Home</Link>
                    <Link to="/Mybook" className="link">My Book</Link>
                </div>
                <div className="icon-links">
                    <Link to="/Inbox" className="link">
                        <Icon.Inbox />
                    </Link>
                    <Link to="/profile" className="link">
                        <Icon.PersonCircle />
                    </Link>
                </div>
            </div>
            
        </nav>
    );
};

export default Navbar;