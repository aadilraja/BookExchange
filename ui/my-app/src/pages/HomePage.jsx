import React from 'react';
import stackOfBooks from "..\\assets\\homepage\\Books-img.png";
import "../styles/HomePage.css";

function Home() {
  return (
    <div className="landing-page">
     

      {/* Main Content Section */}
      <div className="content-container">
        {/* Left Content */}
        <div className="text-section">
          <h1>
            Share Book <br />
            Discover Stories <br />
            Connect with Readers!
          </h1>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
            Faucibus in libero risus semper habitant. Etiam et integer 
            facilisi eget. Lorem ipsum dolor.
          </p>
          <button className="add-book-btn">Add Book</button>
        </div>

        {/* Right Content: Yellow shape + Book Stack image */}
        <div className="image-section">
          <div className="yellow-shape"></div>
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