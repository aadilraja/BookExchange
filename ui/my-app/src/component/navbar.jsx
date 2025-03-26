import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import * as Icon from 'react-bootstrap-icons';
import Logo from '../assets/logo.svg';
import '../Styles/navbar.css';

const Navbar = () => {
    const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
    const [isMobile, setIsMobile] = useState(window.innerWidth <= 768);

    const toggleMobileMenu = () => {
        setMobileMenuOpen(!mobileMenuOpen);
    };

    // Close mobile menu when a link is clicked
    const closeMobileMenu = () => {
        setMobileMenuOpen(false);
    };

    // Handle responsive design
    useEffect(() => {
        const handleResize = () => {
            setIsMobile(window.innerWidth <= 768);
            // Automatically close mobile menu on larger screens
            if (window.innerWidth > 768) {
                setMobileMenuOpen(false);
            }
        };

        // Add event listener
        window.addEventListener('resize', handleResize);

        // Clean up event listener
        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    return (
        <header>
            <nav className="navbar">
                <div className="logo-container">
                    <Link to="/" className='logo-Link'>
                        <img src={Logo} alt="Logo" className='Logo' />
                    </Link>
                </div>
                
                {isMobile && (
                    <div className="mobile-menu-toggle" onClick={toggleMobileMenu}>
                        {mobileMenuOpen ? <Icon.X size={24} /> : <Icon.List size={24} />}
                    </div>
                )}
                
                <div className={`links-container ${mobileMenuOpen ? 'mobile-active' : ''}`}>
                    <div className="text-links">
                        <Link to="/" className="link" onClick={closeMobileMenu}>Home</Link>
                        <Link to="/Mybook" className="link" onClick={closeMobileMenu}>My Book</Link>
                    </div>
                    <div className="icon-links">
                        <Link to="/Inbox" className="link" onClick={closeMobileMenu}>
                            <Icon.Inbox />
                        </Link>
                        <Link to="/profile" className="link" onClick={closeMobileMenu}>
                            <Icon.PersonCircle />
                        </Link>
                    </div>
                </div>
            </nav>
        </header>
    );
};

export default Navbar;