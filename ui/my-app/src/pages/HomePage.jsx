import React from 'react';
import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import stackOfBooks from "..\\assets\\homepage\\Books-img.png";
import yellowDecor from "../assets/decor.png";


import "../styles/HomePage.css";

function Home() {
  const navigate = useNavigate();


  const handleAddBook = () => {
    console.log("Add Book button clicked");
    try {
      navigate("/AddBook");
     


    } catch (error) {
      console.error("Error occurred while redirecting to Add Book page: ", error);
    }
  }

  return (
    <div className="landing-page">
     

      <div className="content-container">
        <div className="text-section">
          <h1>
            Share <span className='yellow-text'>Book</span> <br/>
            Discover <span className='yellow-text'>Stories</span><br/>
            Connect with<br/> <span className='yellow-text'>Readers!</span>
          </h1>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
            Faucibus in libero risus semper habitant. Etiam et integer 
            facilisi eget. Lorem ipsum dolor.
          </p>
          <button className="add-book-btn" onClick={handleAddBook}><span>Add Book</span></button>
        </div>

        <div className="image-section">
         
          {/* <img src={yellowDecor} alt="Yellow shape"
            className="yellow-Decor" /> */}

          
          <img
            src={stackOfBooks}
            alt="Stack of books"
            className="books-image"
          />
        </div>
      </div>
    </div>
  );
}

export default Home;